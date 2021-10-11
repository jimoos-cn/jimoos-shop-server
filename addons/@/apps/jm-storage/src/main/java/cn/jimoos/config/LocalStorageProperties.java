package cn.jimoos.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalStorageProperties {
    public final static String KEY = "local.storage";
    private String rootPath;
    private String host;
}
