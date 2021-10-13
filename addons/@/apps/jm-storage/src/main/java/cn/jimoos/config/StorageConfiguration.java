package cn.jimoos.config;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class StorageConfiguration {
    @Resource
    BaseSettingsService baseSettingsService;

    @PostConstruct
    public void getStorageProperties() {
        StorageProperties storageProperties =
                baseSettingsService.getObjectByKeyword(StorageProperties.KEY, StorageProperties.class);
        if (storageProperties == null) {
            defaultStorageProperties();
        }
    }

    private void defaultStorageProperties() {
        StorageProperties defaultProperties = new StorageProperties();
        defaultProperties.setLocalStorage(true);
        defaultProperties.setHuaweiObs(false);
        BaseSettingsCreateForm form = new BaseSettingsCreateForm();
        form.setKeyword(StorageProperties.KEY);
        form.setContent(JsonMapper.INSTANCE.toJson(defaultProperties));
        baseSettingsService.save(form);
    }
}
