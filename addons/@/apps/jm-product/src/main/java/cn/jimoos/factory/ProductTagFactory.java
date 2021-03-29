package cn.jimoos.factory;

import cn.jimoos.entity.ProductTagEntity;
import cn.jimoos.form.tag.BeProductTagCreateForm;
import cn.jimoos.repository.ProductTagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:59.
 */
@Component
public class ProductTagFactory {
    @Resource
    ProductTagRepository productTagRepository;

    /**
     * 创建 ProductTagEntity
     *
     * @param productTagForm
     * @return
     */
    public ProductTagEntity create(BeProductTagCreateForm productTagForm) {
        Long now = System.currentTimeMillis();
        ProductTagEntity productTagEntity = new ProductTagEntity(productTagRepository);
        BeanUtils.copyProperties(productTagForm, productTagEntity);
        productTagEntity.setCreateAt(now);
        productTagEntity.setUpdateAt(now);
        productTagEntity.setDeleted(false);
        return productTagEntity;
    }
}
