package cn.jimoos.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageProperties {
    public final static String KEY = "storage";
    private Boolean localStorage;
    private Boolean huaweiObs;
}
