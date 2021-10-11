package cn.jimoos.service;

import cn.jimoos.config.LocalStorageProperties;
import cn.jimoos.config.StorageProperties;
import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    @Resource
    private String rootPath;

    @Resource
    private String host;

    @Resource
    BaseSettingsService baseSettingsService;

    public String upload(MultipartFile file, int type) throws IOException {
        String oldName = file.getOriginalFilename();
        Assert.notNull(oldName, "文件名为空");
        String folderPath = type == 0 ? "image/" : "media/";
        File folder = new File(rootPath + folderPath);
        if (!folder.isDirectory()) {
            folder.mkdir();
        }
        String newName = System.currentTimeMillis() +
                oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(rootPath + folderPath + newName));
        return host + folderPath + newName;
    }

    public void delete(String url) {
        String[] split = url.split("/");
        String folder = split[split.length - 2];
        String fileName = split[split.length - 1];
        String filePath = rootPath + "/" + folder + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public StorageProperties checkStorage() {
        StorageProperties storageProperties =
                baseSettingsService.getObjectByKeyword(StorageProperties.KEY, StorageProperties.class);
        return storageProperties == null ? defaultStorage() : storageProperties;
    }

    public StorageProperties changeStorage() {
        StorageProperties storageProperties =
                baseSettingsService.getObjectByKeyword(StorageProperties.KEY, StorageProperties.class);
        Boolean temp = storageProperties.getLocalStorage();
        storageProperties.setLocalStorage(!temp);
        storageProperties.setHuaweiObs(temp);
        String content = JsonMapper.INSTANCE.toJson(storageProperties);
        baseSettingsService.saveContentByKeyword(content, StorageProperties.KEY);
        return storageProperties;
    }

    private StorageProperties defaultStorage() {
        StorageProperties storageProperties = new StorageProperties(true, false);
        BaseSettingsCreateForm form = new BaseSettingsCreateForm();
        form.setContent(JsonMapper.INSTANCE.toJson(storageProperties));
        form.setKeyword(StorageProperties.KEY);
        baseSettingsService.save(form);

        LocalStorageProperties localStorageProperties =
                baseSettingsService.getObjectByKeyword(LocalStorageProperties.KEY, LocalStorageProperties.class);
        if (localStorageProperties == null) {
            localStorageProperties = new LocalStorageProperties(rootPath, host);
            form.setContent(JsonMapper.INSTANCE.toJson(localStorageProperties));
            form.setKeyword(LocalStorageProperties.KEY);
            baseSettingsService.save(form);
        }
        return storageProperties;
    }
}
