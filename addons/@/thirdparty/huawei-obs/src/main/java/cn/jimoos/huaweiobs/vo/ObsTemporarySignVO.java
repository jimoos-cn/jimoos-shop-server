package cn.jimoos.huaweiobs.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/11/18 16:20
 */
@Data
public class ObsTemporarySignVO {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 上传成功后的返回值
     */
    private String blobUrl;
    /**
     * 过期时间
     */
    private String expireAt;
    /**
     * policy
     */
    private String policy;
    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     * signature
     */
    private String signature;
    /**
     * x-obs-acl
     */
    @JsonProperty(value = "xObsAcl")
    private String xObsAcl;
    /**
     * key
     */
    private String key;
}
