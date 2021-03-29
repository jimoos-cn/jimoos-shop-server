package cn.jimoos.entity;

import cn.jimoos.form.tag.BeProductTagUpdateForm;
import cn.jimoos.model.ProductTag;
import cn.jimoos.repository.ProductTagRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @date 2021-03-29 20:52.
 */
public class ProductTagEntity extends ProductTag {

    @Resource
    @JsonIgnore
    private ProductTagRepository productTagRepository;


    public ProductTagEntity() {
    }

    public ProductTagEntity(ProductTagRepository productTagRepository) {
        this.productTagRepository = productTagRepository;
    }

    public void update(BeProductTagUpdateForm form) {
        this.setName(form.getName());
        this.setType(form.getType());
        this.setColor(form.getColor());
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 是否 绑定了商品
     *
     * @return true 绑定了 false 未绑定
     */
    public boolean ifBind() {
        if (this.getId() == null) {
            return false;
        }
        return productTagRepository.countProductNumByTagId(this.getId()) > 0;
    }
}
