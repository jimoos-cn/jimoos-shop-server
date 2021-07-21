package cn.jimoos.factory;

import cn.jimoos.entity.ProductSkuEntity;
import cn.jimoos.form.sku.BeProductSkuAddForm;
import cn.jimoos.form.sku.BeProductSkuSearchForm;
import cn.jimoos.form.sku.BeProductSkuUpdateForm;
import cn.jimoos.repository.ProductSkuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/7/20 14:00:16
 * @description
 */
@Component
public class ProductSkuFactory {
    @Resource
    ProductSkuRepository productSkuRepository;

    public ProductSkuEntity create(BeProductSkuAddForm beProductSkuAddForm) {
        ProductSkuEntity productSkuEntity = new ProductSkuEntity(productSkuRepository);
        BeanUtils.copyProperties(beProductSkuAddForm, productSkuEntity);
        return productSkuEntity;
    }

    public ProductSkuEntity create(BeProductSkuUpdateForm beProductSkuUpdateForm) {
        ProductSkuEntity productSkuEntity = new ProductSkuEntity(productSkuRepository);
        BeanUtils.copyProperties(beProductSkuUpdateForm, productSkuEntity);
        if (productSkuEntity.getId() == null) {
            // 对新插入数据进行数据处理
            productSkuEntity.forInsert();
        }
        return productSkuEntity;
    }

    public ProductSkuEntity create(BeProductSkuSearchForm beProductSkuSearchForm) {
        ProductSkuEntity productSkuEntity = new ProductSkuEntity(productSkuRepository);
        BeanUtils.copyProperties(beProductSkuSearchForm, productSkuEntity);
        return productSkuEntity;
    }

    public ProductSkuEntity create() {
        return new ProductSkuEntity(productSkuRepository);
    }
}
