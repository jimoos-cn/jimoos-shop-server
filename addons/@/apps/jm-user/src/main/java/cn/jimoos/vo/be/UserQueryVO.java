package cn.jimoos.vo.be;

import cn.jimoos.user.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 后台的用户查询VO back-end
 *
 * @author :keepcleargas
 * @date :2021-01-15 10:39.
 */
@Data
public class UserQueryVO {
    /**
     * id
     */
    private Long id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 0 未禁止 1 已禁止
     */
    private Boolean ban;

    /**
     * 角色
     */
    private Byte role;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String area;

    public static final UserQueryVO fromUser(User user) {
        UserQueryVO userQueryVO = new UserQueryVO();
        BeanUtils.copyProperties(user, userQueryVO);
        return userQueryVO;
    }
}
