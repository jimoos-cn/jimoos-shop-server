package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.attr.*;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductAttrVO;

import java.util.List;

/**
 * 销售属性管理
 *
 * @author :keepcleargas
 * @date :2021-03-30 14:20.
 */
public interface ProductAttrService {
    /**
     * 添加 销售属性名称
     *
     * @param productAttrForm Product Attr Form
     * @return ProductAttrVO
     */
    ProductAttrVO addAttr(BeProductAttrForm productAttrForm);

    /**
     * 更新 销售属性名称
     *
     * @param productAttrForm Product Attr Form
     * @return ProductAttrVO
     */
    ProductAttrVO updateAttr(BeProductAttrForm productAttrForm) throws BussException;

    /**
     * 删除销售属性
     *
     * @param form Product Attr Delete Form
     * @return affectNum
     */
    int delete(BeProductAttrDeleteForm form) throws BussException;

    /**
     * 查询 销售属性
     *
     * @param form Product Attr Query Form
     * @return Page<ProductAttr>
     */
    Page<ProductAttr> query(BeProductAttrQueryForm form);

    /**
     * 查询 销售属性的 值的列表
     *
     * @param attrId Attr Id
     * @return List<ProductAttrValue> 值的列表
     */
    List<ProductAttrValue> attrValues(Long attrId) throws BussException;

    /**
     * 更新 销售属性值
     *
     * @param form Attr Value Form
     * @return AffectNum
     */
    ProductAttrValue updateAttrValue(BeAttrValueForm form);

    /**
     * 批量更新 销售属性值
     *
     * @param attrValuesForm attr values form
     * @return List<ProductAttrValue>
     */
    List<ProductAttrValue> saveAttrValues(BeAttrValuesForm attrValuesForm) throws BussException;

    /**
     * 删除 销售属性值
     *
     * @param attrValueDeleteForm attr Value Delete Form
     * @return affectNum
     */
    int deleteAttrValue(BeAttrValueDeleteForm attrValueDeleteForm) throws BussException;
}
