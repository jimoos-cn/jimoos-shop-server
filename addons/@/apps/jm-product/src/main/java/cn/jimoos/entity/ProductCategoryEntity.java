package cn.jimoos.entity;

import cn.jimoos.form.category.BeProductCateUpdateForm;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.repository.ProductCategoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @date 2021/03/29 17:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductCategoryEntity extends ProductCategory {

    @Resource
    @JsonIgnore
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategoryEntity() {
    }

    public ProductCategoryEntity(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public void update(BeProductCateUpdateForm form) {
        this.setName(form.getName());
        this.setDescription(form.getDescription());
        this.setSort(form.getSort());
        this.setImgUrl(form.getImgUrl());
        this.setPid(form.getPid());
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 是否被绑定
     */
    public boolean ifBind() {
        if (this.getId() == null) {
            return false;
        }
        return productCategoryRepository.productTotalNum(this.getId()) > 0;
    }
}
