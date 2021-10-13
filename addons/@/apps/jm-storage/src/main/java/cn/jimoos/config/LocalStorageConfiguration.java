package cn.jimoos.config;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@Slf4j
public class LocalStorageConfiguration {
    private LocalStorageProperties localStorageProperties;

    @Resource
    BaseSettingsService baseSettingsService;

    @PostConstruct
    public void getLocalStorageProperties() {
        LocalStorageProperties localStorageProperties =
                baseSettingsService.getObjectByKeyword(LocalStorageProperties.KEY, LocalStorageProperties.class);
        if (localStorageProperties == null) {
            localStorageProperties = defaultLocalStorageProperties();
        }
        this.localStorageProperties = localStorageProperties;
    }

    private LocalStorageProperties defaultLocalStorageProperties() {
        LocalStorageProperties defaultProperties = new LocalStorageProperties();
        defaultProperties.setRootPath(System.getProperty("java.io.tmpdir"));
        defaultProperties.setHost("http://127.0.0.1:9001/storage/");
        BaseSettingsCreateForm form = new BaseSettingsCreateForm();
        form.setKeyword(LocalStorageProperties.KEY);
        form.setContent(JsonMapper.INSTANCE.toJson(defaultProperties));
        baseSettingsService.save(form);
        return defaultProperties;
    }

    @Bean(name = "rootPath")
    public String rootPath() {
        String rootPath = localStorageProperties.getRootPath();
        if (StringUtils.isEmpty(rootPath)) {
            rootPath = System.getProperty("java.io.tmpdir");
        }
        return rootPath;
    }

    @Bean(name = "host")
    public String host() {
        String host = localStorageProperties.getHost();
        if (StringUtils.isEmpty(host)) {
            host = "http://127.0.0.1:9001/storage/";
        }
        return host;
    }
}
