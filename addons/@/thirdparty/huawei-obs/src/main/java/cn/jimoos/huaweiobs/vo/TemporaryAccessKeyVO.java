package cn.jimoos.huaweiobs.vo;

import lombok.Data;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/1/7 18:47
 */
@Data
public class TemporaryAccessKeyVO {
    /**
     * 过期时间
     */
    private String expiresAt;
    /**
     * access
     */
    private String access;
    /**
     * secret
     */
    private String secret;

    /**
     * securitytoken
     */
    private String securitytoken;
    /**
     * bucketName
     */
    private String bucketName;
    /**
     * endpoint
     */
    private String endpoint;

}
