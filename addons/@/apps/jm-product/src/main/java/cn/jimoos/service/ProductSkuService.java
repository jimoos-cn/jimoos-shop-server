package cn.jimoos.service;

import cn.jimoos.form.sku.BeProductSkuUpdateForm;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/20 11:41:45
 * @description
 */
public interface ProductSkuService {
    /**
     * 删除某商品的sku (软删除)
     *
     * @param skuId skuID
     */
    boolean deleteProductSku(Long skuId);

    /**
     * 更新某商品的sku列表
     *
     * @param beProductSkuUpdateForms 新增表单
     * @return
     */
    boolean updateProductSku(List<BeProductSkuUpdateForm> beProductSkuUpdateForms);
}
