package cn.jimoos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    @Value("${local.storage.root-path}")
    private String ROOT_PATH;

    public String upload(MultipartFile file, int type, HttpServletRequest request) throws IOException {
        String oldName = file.getOriginalFilename();
        Assert.notNull(oldName, "文件名为空");
        String folderPath = type == 0 ? "image/" : "media/";
        File folder = new File(ROOT_PATH + folderPath);
        if (!folder.isDirectory()) {
            folder.mkdir();
        }
        String newName = System.currentTimeMillis() +
                oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(ROOT_PATH + folderPath + newName));
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()  + "/storage/" + folderPath + newName;
    }

    public void delete(String url) {
        String[] split = url.split("/");
        String folder = split[split.length - 2];
        String fileName = split[split.length - 1];
        String filePath = ROOT_PATH + "/" + folder + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
