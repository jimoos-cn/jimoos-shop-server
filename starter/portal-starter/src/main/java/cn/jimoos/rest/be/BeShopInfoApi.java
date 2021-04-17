package cn.jimoos.rest.be;

import cn.jimoos.form.be.BeShopInfoForm;
import cn.jimoos.model.ShopInfo;
import cn.jimoos.service.ShopInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:25.
 */
@RestController
@RequestMapping("/bAdmin/v1/shopInfo")
public class BeShopInfoApi {
    @Resource
    ShopInfoService shopInfoService;

    /**
     * 修改商城 介绍信息
     *
     * @return ShopInfo
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public ShopInfo getShopInfo(@ModelAttribute BeShopInfoForm shopInfoForm) {
        return shopInfoService.saveShopInfo(shopInfoForm);
    }
}
