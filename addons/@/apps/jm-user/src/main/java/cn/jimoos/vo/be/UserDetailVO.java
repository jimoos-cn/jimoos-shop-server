package cn.jimoos.vo.be;

import cn.jimoos.model.UserRelation;
import cn.jimoos.model.UserSocial;
import cn.jimoos.user.model.UserAddress;
import lombok.Data;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/15 09:42:08
 * @description 后台查看的用户详细表
 */
@Data
public class UserDetailVO {
    /**
     * 收货地址表
     */
    List<UserAddress> userAddresses;

    /**
     * 用户分销关系表
     */
    UserRelation userRelation;

    /**
     * 用户社交登陆
     */
    List<UserSocial> userSocials;

    /**
     * 用户主表
     */
    UserQueryVO user;
}
