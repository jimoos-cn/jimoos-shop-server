package cn.jimoos.service;

import cn.jimoos.form.be.BeShopInfoForm;
import cn.jimoos.model.ShopInfo;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:17.
 */
public interface ShopInfoService {
    /**
     * 获取 商城信息
     *
     * @param beShopInfoForm back-end shop info form
     * @return ShopInfo
     */
    ShopInfo saveShopInfo(BeShopInfoForm beShopInfoForm);

    /**
     * 获取商城信息
     *
     * @return ShopInfo
     */
    ShopInfo getOne();
}
