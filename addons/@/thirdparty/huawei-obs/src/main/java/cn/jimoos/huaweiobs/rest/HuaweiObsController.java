package cn.jimoos.huaweiobs.rest;

import cn.jimoos.huaweiobs.form.ObsTemporarySignForm;
import cn.jimoos.huaweiobs.service.HuaweiObsService;
import cn.jimoos.huaweiobs.vo.ObsTemporarySignVO;
import cn.jimoos.huaweiobs.vo.TemporaryAccessKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/11/23 11:11
 */
@RequestMapping(value = "/v1/huawei/obs")
@Slf4j
@RestController
public class HuaweiObsController {
    @Resource
    HuaweiObsService huaweiObsService;

    /**
     * 使用预签名URL方式访问OBS
     */
    @PostMapping(value = "/temporary-signature", produces = "application/json; charset=utf-8")
    public List<ObsTemporarySignVO> getTemporarySignature(@Valid @RequestBody ObsTemporarySignForm form) {
        return huaweiObsService.getTemporarySignature(form);
    }

    /**
     * 获取aliyun oss图片上传的token
     *
     * @param
     * @return
     */
    @GetMapping(value = "/img", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getImageUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(0);
    }

    /**
     * 获取aliyun oss音视频上传的token
     *
     * @param
     * @return
     */
    @GetMapping(value = "/video", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getVideoUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(1);
    }
}
