package cn.jimoos.rest.be;

import cn.jimoos.config.StorageProperties;
import cn.jimoos.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RequestMapping(value = "/bAdmin/v1/storage")
@Slf4j
@RestController
public class BeStorageApi {
    @Resource
    private StorageService storageService;

    @PostMapping(value = "/upload", produces = "application/json; charset=utf-8")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("type") int type) throws IOException {
        return storageService.upload(file, type);
    }

    @DeleteMapping(value = "/delete", produces = "application/json; charset=utf-8")
    public void delete(@RequestParam("url") String url) {
        storageService.delete(url);
    }

    @GetMapping(value = "/checkStorage", produces = "application/json; charset=utf-8")
    public StorageProperties checkStorage() {
        return storageService.checkStorage();
    }

    @PostMapping(value = "/changeStorage", produces = "application/json; charset=utf-8")
    public StorageProperties changeStorage() {
        return storageService.changeStorage();
    }
}
