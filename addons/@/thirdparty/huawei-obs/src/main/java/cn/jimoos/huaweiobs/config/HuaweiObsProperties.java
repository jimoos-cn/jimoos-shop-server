package cn.jimoos.huaweiobs.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/11/23 10:56
 */
@Data
public class HuaweiObsProperties {
    public static final String KEY = "huawei.obs";
    private String accessKey;
    private String secretKey;
    private String endPoint;
    /**
     * 失效时间（分钟）
     */
    private Long duration = 3600L;
    /**
     * buckets
     */
    private List<Bucket> buckets;

    @Data
    public static final class Bucket {
        private Integer type;
        private String bucketName;
    }

    @JsonIgnore
    public String getBucketNameByType(Integer type) {
        for (Bucket bucket : buckets) {
            if (type.equals(bucket.getType())) {
                return bucket.getBucketName();
            }
        }
        throw new IllegalArgumentException("Bucket配置不全！");
    }
}
