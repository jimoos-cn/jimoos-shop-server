package cn.jimoos.service.impl;

import cn.jimoos.dao.ShopInfoMapper;
import cn.jimoos.form.be.BeShopInfoForm;
import cn.jimoos.model.ShopInfo;
import cn.jimoos.service.ShopInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:18.
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {
    @Resource
    ShopInfoMapper shopInfoMapper;

    @Override
    public ShopInfo saveShopInfo(BeShopInfoForm beShopInfoForm) {
        ShopInfo shopInfo = shopInfoMapper.selectOne();
        if (shopInfo == null) {
            shopInfo = new ShopInfo();
            BeanUtils.copyProperties(beShopInfoForm, shopInfo);
            shopInfo.setCreateAt(System.currentTimeMillis());
            shopInfoMapper.insert(shopInfo);
        } else {
            BeanUtils.copyProperties(beShopInfoForm, shopInfo);
            shopInfo.setUpdateAt(System.currentTimeMillis());
            shopInfoMapper.updateByPrimaryKey(shopInfo);
        }

        return shopInfo;
    }

    @Override
    public ShopInfo getOne() {
        return shopInfoMapper.selectOne();
    }
}
