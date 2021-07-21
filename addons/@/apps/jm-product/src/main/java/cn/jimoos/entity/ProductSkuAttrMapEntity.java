package cn.jimoos.entity;

import cn.jimoos.model.ProductSkuAttrMap;

/**
 * @author SiletFlower
 * @date 2021/7/21 19:45:27
 * @description
 */
public class ProductSkuAttrMapEntity extends ProductSkuAttrMap {
    public void delete(){
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
