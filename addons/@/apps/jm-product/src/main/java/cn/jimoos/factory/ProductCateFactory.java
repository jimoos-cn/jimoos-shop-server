package cn.jimoos.factory;

import cn.jimoos.entity.ProductCategoryEntity;
import cn.jimoos.form.category.BeProductCateCreateForm;
import cn.jimoos.repository.ProductCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:18.
 */
@Component
public class ProductCateFactory {
    @Resource
    ProductCategoryRepository productCategoryRepository;

    /**
     * 创建 ProductCategoryEntity
     *
     * @param productCategoryForm
     * @return
     */
    public ProductCategoryEntity create(BeProductCateCreateForm productCategoryForm) {
        Long now = System.currentTimeMillis();
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity(productCategoryRepository);
        BeanUtils.copyProperties(productCategoryForm, productCategoryEntity);
        productCategoryEntity.setCreateAt(now);
        productCategoryEntity.setUpdateAt(now);
        productCategoryEntity.setDeleted(false);
        return productCategoryEntity;
    }
}
