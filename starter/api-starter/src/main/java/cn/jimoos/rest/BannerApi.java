package cn.jimoos.rest;

import cn.jimoos.service.BannerService;
import cn.jimoos.vo.BannerVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-01-20 13:45.
 */
@RestController
@RequestMapping("/v1/banners")
public class BannerApi {
    @Resource
    BannerService bannerService;

    /**
     * app根据位置获取banner列表
     *
     * @param position banner 投放位置，自定义
     * @return List<BannerVO>
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<BannerVO> getBannersByPosition(@RequestParam("position") Integer position) {
        return bannerService.query(position);
    }
}
