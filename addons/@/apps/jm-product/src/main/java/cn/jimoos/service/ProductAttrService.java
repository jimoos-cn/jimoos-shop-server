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
     * 获取 销售属性名称
     *
     * @param id attr id
     * @return ProductAttrVO
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    ProductAttrVO getOne(Long id) throws BussException;

    /**
     * 更新 销售属性名称
     *
     * @param productAttrForm Product Attr Form
     * @return ProductAttrVO
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    ProductAttrVO updateAttr(BeProductAttrForm productAttrForm) throws BussException;

    /**
     * 删除销售属性
     *
     * @param form Product Attr Delete Form
     * @return affectNum
     * @throws BussException ProductError.ATTR_USED | ProductError.ATTR_NOT_FOUND
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
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    List<ProductAttrValue> attrValues(Long attrId) throws BussException;

    /**
     * 更新 销售属性值
     *
     * @param form Attr Value Form
     * @return AffectNum
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    ProductAttrValue updateAttrValue(BeAttrValueForm form) throws BussException;

    /**
     * 批量更新 销售属性值
     *
     * @param attrValuesForm attr values form
     * @return List<ProductAttrValue>
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    List<ProductAttrValue> saveAttrValues(BeAttrValuesForm attrValuesForm) throws BussException;

    /**
     * 删除 销售属性值
     *
     * @param attrValueDeleteForm attr Value Delete Form
     * @return affectNum
     * @throws BussException ProductError.ATTR_NOT_FOUND | ATTR_VALUE_USED
     */
    int deleteAttrValue(BeAttrValueDeleteForm attrValueDeleteForm) throws BussException;
}
