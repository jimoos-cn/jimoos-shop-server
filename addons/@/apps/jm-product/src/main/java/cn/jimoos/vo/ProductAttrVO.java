package cn.jimoos.vo;

import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAttrVO extends ProductAttr {
    private List<ProductAttrValue> attrValues;
}
