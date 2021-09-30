package cn.jimoos.rest.be;

import cn.jimoos.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping(value = "/bAdmin/v1/storage")
@Slf4j
@RestController
public class BeStorageApi {
    @Resource
    private StorageService storageService;

    @PostMapping(value = "/upload", produces = "application/json; charset=utf-8")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("type") int type,
                         HttpServletRequest request) throws IOException {
        return storageService.upload(file, type, request);
    }

    @DeleteMapping(value = "/delete", produces = "application/json; charset=utf-8")
    public void delete(@RequestParam("url") String url) {
        storageService.delete(url);
    }
}
