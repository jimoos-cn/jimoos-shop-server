package cn.jimoos.rest;

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
 * The type Huawei obs api.
 *
 * @author wangyiwen
 * @version 1.0 Created in 2020/11/23 11:11
 */
@RequestMapping(value = "/v1/huawei/obs")
@Slf4j
@RestController
public class HuaweiObsApi {
    /**
     * The Huawei obs service.
     */
    @Resource
    HuaweiObsService huaweiObsService;

    /**
     * 使用预签名URL方式访问 OBS
     *
     * @param form the form
     * @return the temporary signature
     */
    @Deprecated
    @PostMapping(value = "/temporary-signature", produces = "application/json; charset=utf-8")
    public List<ObsTemporarySignVO> getTemporarySignature(@Valid @RequestBody ObsTemporarySignForm form) {
        return huaweiObsService.getTemporarySignature(form);
    }

    /**
     * 获取huawei obs图片上传的token
     *
     * @return image upload token
     */
    @GetMapping(value = "/img", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getImageUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(0);
    }

    /**
     * 获取huawei obs音视频上传的token
     *
     * @return video upload token
     */
    @GetMapping(value = "/video", produces = "application/json; charset=utf-8")
    public TemporaryAccessKeyVO getVideoUploadToken() {
        return huaweiObsService.getTemporaryAccessKey(1);
    }
}
