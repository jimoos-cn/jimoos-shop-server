package cn.jimoos.user.model;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-03-23 10:02.
 */
@Data
public class UserSession {
    /**
     * id
     */
    private Integer id;

    /**
     * token
     */
    private String token;

    /**
     * 平台类型
     */
    private Integer platform;

    /**
     * 设备
     */
    private String device;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 过期时间
     */
    private Long expireAt;

    /**
     * 创建时间
     */
    private Long createAt;
}