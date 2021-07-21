package cn.jimoos.service.impl;

import cn.jimoos.entity.ProductSkuEntity;
import cn.jimoos.factory.ProductSkuFactory;
import cn.jimoos.form.sku.BeProductSkuUpdateForm;
import cn.jimoos.service.ProductSkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/20 11:42:12
 * @description
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    ProductSkuFactory productSkuFactory;

    @Override
    public boolean deleteProductSku(Long skuId) {
        ProductSkuEntity productSkuEntity = productSkuFactory.create();
        productSkuEntity.setId(skuId);
        productSkuEntity.delete();
        productSkuEntity.deleteAttr();
        return productSkuEntity.save();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProductSku(List<BeProductSkuUpdateForm> beProductSkuUpdateForms) {
        final boolean[] flag = {true};
        beProductSkuUpdateForms.forEach(form ->{
            ProductSkuEntity productSkuEntity = productSkuFactory.create(form);
            // 将原数据进行假删除
            if(flag[0]){
                flag[0] = false;
                productSkuEntity.deleteByProductId();
            }
            // 设置删除状态为false，保证原来的数据能更新
            productSkuEntity.setDeleted(false);
            productSkuEntity.save();
        });
        return true;
    }
}
