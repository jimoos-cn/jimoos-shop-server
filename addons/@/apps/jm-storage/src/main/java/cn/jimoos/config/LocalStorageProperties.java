package cn.jimoos.config;

import lombok.Data;

@Data
public class LocalStorageProperties {
    public final static String KEY = "local.storage";
    private String rootPath;
}
