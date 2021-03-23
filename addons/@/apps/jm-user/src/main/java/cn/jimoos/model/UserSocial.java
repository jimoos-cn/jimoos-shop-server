package cn.jimoos.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户社交登录
 *
 * @author :keepcleargas
 * @date :2020-11-25 23:32.
 */
@Data
@TableName("t_user_social")
public class UserSocial {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 平台id
     */
    private String socialId;

    /**
     * 平台id
     */
    private String socialUnionId;

    /**
     * 平台类型 由客户端定义 0:未知,1:wechat,2:qq 3:微博
     */
    private Byte type;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 其他信息
     */
    private String other;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}