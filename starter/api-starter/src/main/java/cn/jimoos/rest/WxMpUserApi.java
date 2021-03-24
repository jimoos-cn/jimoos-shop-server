package cn.jimoos.rest;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.config.WxMaConfiguration;
import cn.jimoos.error.UserError;
import cn.jimoos.form.SocialRegForm;
import cn.jimoos.service.UserService;
import cn.jimoos.user.constant.SocialConstant;
import cn.jimoos.user.vo.UserVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号/小程序登录
 *
 * @author :keepcleargas
 * @date :2021-03-23 16:01.
 */
@RestController
@RequestMapping("/v1/users")
@Slf4j
public class WxMpUserApi {
    @Resource
    UserService userService;

    /**
     * 微信小程序登录
     *
     * @param code 授权码
     * @return 用户信息
     * @throws BussException ErrorCodeDefine.FORM_PARAMS_NOT_VALID
     */
    @PostMapping(value = "/wxMaLogin", produces = "application/json; charset=utf-8")
    public UserVO wxLogin(@RequestParam("code") String code,
                          @RequestParam("encryptedData") String encryptedData,
                          @RequestParam("iv") String iv) throws BussException {
        if (StringUtils.isEmpty(code)) {
            throw new BussException(ErrorCodeDefine.FORM_PARAMS_NOT_VALID);
        }

        final WxMaService wxService = WxMaConfiguration.getDefaultMaService();

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            SocialRegForm socialRegForm = new SocialRegForm();
            socialRegForm.setUnionId(session.getUnionid());
            socialRegForm.setSocialId(session.getOpenid());

            WxMaPhoneNumberInfo wxMaPhoneNumberInfo = wxService.getUserService().getPhoneNoInfo(session.getSessionKey(), encryptedData, iv);

            //微信登录 默认 0
            socialRegForm.setSocialType(SocialConstant.WX_MA_SOCIAL_TYPE);
            socialRegForm.setDevice("小程序");
            socialRegForm.setPlatform(0);
            socialRegForm.setPhone(wxMaPhoneNumberInfo.getPhoneNumber());

            UserVO userVo = userService.maReg(socialRegForm);
            Map<String, String> map = Maps.newHashMapWithExpectedSize(3);
            map.put("socialId", socialRegForm.getSocialId());
            map.put("unionId", socialRegForm.getUnionId());
            map.put("sessionKey", session.getSessionKey());
            userVo.setExtras(map);
            return userVo;
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new BussException(UserError.WX_LOGIN_ERROR);
        }
    }
}
