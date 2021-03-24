package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.BannerForm;
import cn.jimoos.form.BannerQueryForm;
import cn.jimoos.service.BannerService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.BannerVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-01-20 13:45.
 */
@RestController
@RequestMapping("/bAdmin/v1/banners")
public class BeBannerApi {
    @Resource
    BannerService bannerService;

    /**
     * 后台获取banner列表
     *
     * @param bannerQueryForm 查询表单
     * @return Page<BannerVO>
     */
    @GetMapping(value = "", produces = "application/json; charset=utf-8")
    public Page<BannerVO> getBanners(@ModelAttribute BannerQueryForm bannerQueryForm) {
        return bannerService.backQuery(bannerQueryForm);
    }

    /**
     * 根据bannerId获取banner详情
     *
     * @param bannerId banner Id
     * @return BannerVO
     */
    @GetMapping(value = "/{bannerId}", produces = "application/json; charset=utf-8")
    public BannerVO getBannerById(@PathVariable("bannerId") Long bannerId) throws BussException {
        return bannerService.getBannerById(bannerId);
    }

    /**
     * 新增banner
     *
     * @param bannerForm banner form
     * @return BannerVO
     */
    @PostMapping(value = "", produces = "application/json; charset=utf-8")
    public BannerVO addBanner(@ModelAttribute BannerForm bannerForm) throws BussException {
        return bannerService.addBanner(bannerForm);
    }

    /**
     * 修改banner
     *
     * @param bannerId   banner Id
     * @param bannerForm banner Form
     * @return BannerVO
     */
    @PostMapping(value = "/{bannerId}", produces = "application/json; charset=utf-8")
    public BannerVO updateBanner(@PathVariable("bannerId") Long bannerId, @ModelAttribute BannerForm bannerForm) throws BussException {
        return bannerService.updateBanner(bannerId, bannerForm);
    }

    /**
     * 删除banner
     *
     * @param bannerId banner Id
     */
    @DeleteMapping(value = "/{bannerId}", produces = "application/json; charset=utf-8")
    public void deleteBanner(@PathVariable("bannerId") Long bannerId) throws BussException {
        bannerService.deleteBanner(bannerId);
    }

    /**
     * 上架banner
     *
     * @param bannerId banner Id
     * @throws BussException BannerError.BANNER_NOT_FOUND
     */
    @PostMapping(value = "/{bannerId}/up", produces = "application/json; charset=utf-8")
    public void upBanner(@PathVariable("bannerId") Long bannerId) throws BussException {
        bannerService.upBanner(bannerId);
    }

    /**
     * 下架banner
     *
     * @param bannerId banner ID
     * @throws BussException BannerError.BANNER_NOT_FOUND
     */
    @PostMapping(value = "/{bannerId}/down", produces = "application/json; charset=utf-8")
    public void downBanner(@PathVariable("bannerId") Long bannerId) throws BussException {
        bannerService.downBanner(bannerId);
    }
}
