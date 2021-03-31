package cn.jimoos.factory;

import cn.jimoos.entity.ProductEntity;
import cn.jimoos.form.product.BeProductForm;
import cn.jimoos.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-31 13:50.
 */
@Component
public class ProductFactory {
    @Resource
    ProductRepository productRepository;

    /**
     * 创建 ProductEntity
     *
     * @param form product Form
     * @return ProductEntity
     */
    public ProductEntity create(BeProductForm form) {
        Long now = System.currentTimeMillis();
        ProductEntity productEntity = new ProductEntity(productRepository);
        productEntity.setName(form.getName());
        productEntity.setText(form.getText());
        productEntity.setCover(form.getCover());
        productEntity.setVideoUrl(form.getVideoUrl());
        productEntity.setBannerUrls(form.getBannerUrls());
        productEntity.setSort(form.getSort());
        productEntity.setFakeSales(form.getFakeSales());
        productEntity.setType(ProductEntity.Type.NORMAL.val());
        productEntity.setMerchantId(form.getMerchantId());
        productEntity.setCategoryId(form.getCategoryId());
        productEntity.setStatus(ProductEntity.Status.NOT_LISTED.val());
        productEntity.setUpdateAt(0L);
        productEntity.setCreateAt(now);
        productEntity.setDeleted(false);
        return productEntity;
    }
}
