package cn.jimoos.entity;

import cn.jimoos.form.attr.BeAttrValueForm;
import cn.jimoos.form.attr.BeProductAttrForm;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import cn.jimoos.repository.ProductAttrRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:37.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductAttrEntity extends ProductAttr {
    @Resource
    @JsonIgnore
    ProductAttrRepository productAttrRepository;

    private List<ProductAttrValue> attrValueInputs;

    public ProductAttrEntity() {
    }

    public ProductAttrEntity(ProductAttrRepository productAttrRepository) {
        this.productAttrRepository = productAttrRepository;
    }


    public void update(BeProductAttrForm form) {
        long now = System.currentTimeMillis();
        this.setName(form.getName());
        this.setDescription(form.getDescription());
        this.setUpdateAt(now);
        addAttrValues(form.getAttrValues());
    }

    public List<ProductAttrValue> getAttrValues() {
        return productAttrRepository.findValuesById(this.getId());
    }

    /**
     * 创建 attrValues
     */
    public void addAttrValues(List<BeAttrValueForm> attrValues) {
        Long now = System.currentTimeMillis();
        List<ProductAttrValue> productAttrValues = new ArrayList<>();

        for (BeAttrValueForm attrValue : attrValues) {
            ProductAttrValue entity = new ProductAttrValue();
            entity.setName(attrValue.getName());
            entity.setDescription(attrValue.getDescription());
            entity.setSort(attrValue.getSort());
            entity.setAttrId(this.getId());
            entity.setMerchantId(this.getMerchantId());
            entity.setCreateAt(now);
            entity.setUpdateAt(0L);
            entity.setDeleted(Boolean.FALSE);
            productAttrValues.add(entity);
        }
        this.setAttrValueInputs(productAttrValues);
    }


    /**
     * 是否被绑定
     *
     * @return if bind true,else false
     */
    public boolean ifBind() {
        if (this.getId() == null) {
            return false;
        }
        return getProductAttrRepository().hasAnyBindByAttrId(this.getId());
    }
}
