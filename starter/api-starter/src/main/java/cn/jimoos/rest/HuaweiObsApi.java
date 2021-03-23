package cn.jimoos.rest;

import cn.jimoos.huaweiobs.service.HuaweiObsService;
import cn.jimoos.huaweiobs.vo.TemporaryAccessKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
