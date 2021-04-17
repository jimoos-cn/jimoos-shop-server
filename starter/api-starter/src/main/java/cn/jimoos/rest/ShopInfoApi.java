package cn.jimoos.rest;

import cn.jimoos.model.ShopInfo;
import cn.jimoos.service.ShopInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:21.
 */
@RestController
@RequestMapping("/v1/shopInfo")
public class ShopInfoApi {
    @Resource
    ShopInfoService shopInfoService;

    /**
     * 获取商城 介绍信息
     *
     * @return ShopInfo
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public ShopInfo getShopInfo() {
        return shopInfoService.getOne();
    }
}
