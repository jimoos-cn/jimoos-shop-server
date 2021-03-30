package cn.jimoos.factory;

import cn.jimoos.entity.ProductAttrEntity;
import cn.jimoos.form.attr.BeProductAttrForm;
import cn.jimoos.repository.ProductAttrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-30 15:34.
 */
@Component
public class ProductAttrFactory {
    @Resource
    ProductAttrRepository productAttrRepository;

    /**
     * 创建 ProductAttrEntity
     *
     * @param productAttrForm Product Attr Form
     * @return ProductAttrEntity
     */
    public ProductAttrEntity create(BeProductAttrForm productAttrForm) {
        Long now = System.currentTimeMillis();
        ProductAttrEntity productAttrEntity = new ProductAttrEntity(productAttrRepository);
        BeanUtils.copyProperties(productAttrForm, productAttrEntity);
        productAttrEntity.setCreateAt(now);
        productAttrEntity.setUpdateAt(now);
        productAttrEntity.setDeleted(false);

        productAttrEntity.addAttrValues(productAttrForm.getAttrValues());
        return productAttrEntity;
    }
}
