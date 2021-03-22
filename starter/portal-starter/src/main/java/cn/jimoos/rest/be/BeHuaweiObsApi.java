package cn.jimoos.rest.be;

import cn.jimoos.huaweiobs.form.ObsTemporarySignForm;
import cn.jimoos.huaweiobs.service.HuaweiObsService;
import cn.jimoos.huaweiobs.vo.ObsTemporarySignVO;
import cn.jimoos.huaweiobs.vo.TemporaryAccessKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * The type Be huawei obs api.
 *
 * @author kison
 * @version 1.0 Created in 2020/11/23 11:11
 */
@RequestMapping(value = "/bAdmin/v1/huawei/obs")
@Slf4j
@RestController
public class BeHuaweiObsApi {
    /**
     * The Huawei obs service.
     */
    @Resource
    HuaweiObsService huaweiObsService;

    /**
     * 使用预签名URL方式访问OBS
     *
     * @param form the form
     * @return the temporary signature
     */
    @Deprecated
    @PostMapping(value = "/temporary-signature", produces = "application/json; charset=utf-8")
    public List<ObsTemporarySignVO> getTemporarySignature(@Valid @RequestBody ObsTemporarySignForm form) {
        return huaweiObsService.getTemporarySignature(form);
    }

    /**
     * 获取Huawei obs 图片上传的token
     *
     * @return image upload token
     */
    @GetMapping(value = "/img", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getImageUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(0);
    }

    /**
     * 获取Huawei obs 音视频上传的token
     *
     * @return video upload token
     */
    @GetMapping(value = "/video", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getVideoUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(1);
    }
}
