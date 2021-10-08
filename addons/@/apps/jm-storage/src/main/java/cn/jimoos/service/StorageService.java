package cn.jimoos.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    @Resource
    private String rootPath;

    public String upload(MultipartFile file, int type, HttpServletRequest request) throws IOException {
        String oldName = file.getOriginalFilename();
        Assert.notNull(oldName, "文件名为空");
        String folderPath = type == 0 ? "image/" : "media/";
        String url = ResourceUtils.getURL("classpath:").getPath();
        url += "static/" + folderPath;
        File folder = new File(url);
        if (!folder.isDirectory()) {
            folder.mkdir();
        }
        String newName = System.currentTimeMillis() +
                oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(url + newName));
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + "/" + folderPath + newName;
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
}
