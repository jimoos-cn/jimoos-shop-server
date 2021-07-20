package cn.jimoos.vo.be;

import cn.jimoos.model.UserSocial;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author SiletFlower
 * @date 2021/7/15 17:46:10
 * @description 用户社交登陆
 */
@Data
public class UserSocialVO extends UserSocial {

    public static UserSocialVO toVO(UserSocial userSocial) {
        UserSocialVO userSocialVO = new UserSocialVO();
        BeanUtils.copyProperties(userSocial, userSocialVO);
        return userSocialVO;
    }

}
