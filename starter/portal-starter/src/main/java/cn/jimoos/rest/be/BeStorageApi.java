package cn.jimoos.rest.be;

import cn.jimoos.config.PaymentProperties;
import cn.jimoos.config.StorageProperties;
import cn.jimoos.form.PaymentCreateForm;
import cn.jimoos.form.PaymentUpdateForm;
import cn.jimoos.service.PaymentSelectService;
import cn.jimoos.service.StorageService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "/bAdmin/v1/storage")
@Slf4j
@RestController
public class BeStorageApi {
    @Resource
    private StorageService storageService;

    @Resource
    private PaymentSelectService paymentSelectService;

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

    @GetMapping(value = "/checkPayment", produces = "application/json; charset=utf-8")
    public PaymentProperties checkPayment() {
        return paymentSelectService.checkPayment();
    }

    @PostMapping(value = "/addPayment", produces = "application/json; charset=utf-8")
    public void addPayment(@ModelAttribute PaymentCreateForm form) {
        paymentSelectService.addPayment(form);
    }

    @PostMapping(value = "/changePayment", produces = "application/json; charset=utf-8")
    public void changePayment(PaymentUpdateForm form) {
        paymentSelectService.changePayment(form);
    }
}
