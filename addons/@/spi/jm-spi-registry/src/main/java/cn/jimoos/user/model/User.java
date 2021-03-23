package cn.jimoos.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2020-11-26 21:54.
 */

@Data
@TableName(value = "t_user")
public class User {
    /**
     * id
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 性别
     */
    private Byte gender;

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

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未禁止 1 已禁止
     */
    private Boolean ban;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}