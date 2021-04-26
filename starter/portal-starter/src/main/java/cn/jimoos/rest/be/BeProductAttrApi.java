package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.attr.*;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import cn.jimoos.service.ProductAttrService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductAttrVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:06.
 */
@RestController
@RequestMapping("/bAdmin/v1/product/attrs")
public class BeProductAttrApi {
    @Resource
    ProductAttrService productAttrService;

    /**
     * 查询列表
     *
     * @param form ProductAttr Search Form
     * @return List<ProductAttr>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<ProductAttr> query(@ModelAttribute BeProductAttrQueryForm form) {
        return productAttrService.query(form);
    }

    /**
     * 添加
     *
     * @param form ProductAttr Form
     * @return ProductAttr
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public ProductAttrVO add(@RequestBody BeProductAttrForm form) {
        return productAttrService.addAttr(form);
    }

    /**
     * 更新
     *
     * @param form ProductAttr Update Form
     * @return ProductAttr
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    @PostMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ProductAttrVO update(@RequestBody BeProductAttrForm form) throws BussException {
        return productAttrService.updateAttr(form);
    }

    /**
     * 查询 attr 详情
     *
     * @param id attr id
     * @return ProductAttrVO
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ProductAttrVO getOne(@PathVariable("id") Long id) throws BussException {
        return productAttrService.getOne(id);
    }

    /**
     * 删除
     *
     * @param deleteForm ProductAttr delete form
     * @throws BussException ProductError.ATTR_NOT_FOUND | ProductError.ATTR_USED
     */
    @PostMapping(value = "/{attrId}/delete", produces = "application/json; charset=utf-8")
    public void deleteProductAttr(@ModelAttribute BeProductAttrDeleteForm deleteForm) throws BussException {
        productAttrService.delete(deleteForm);
    }

    /**
     * 查询 attr value list
     *
     * @param id attr id
     * @return List<ProductAttrValue>
     */
    @GetMapping(value = "/{id}/values", produces = "application/json; charset=utf-8")
    public List<ProductAttrValue> attrValues(@PathVariable("id") Long id) throws BussException {
        return productAttrService.attrValues(id);
    }

    /**
     * 更新 attrId 下的 value 列表 ，批量更新
     *
     * @param valuesForm ProductAttr delete form
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    @PostMapping(value = "/{attrId}/values", produces = "application/json; charset=utf-8")
    public List<ProductAttrValue> updateAttrValues(@ModelAttribute BeAttrValuesForm valuesForm) throws BussException {
        return productAttrService.saveAttrValues(valuesForm);
    }

    /**
     * 更新 attrId 下的 value id
     *
     * @param form ProductAttr value update form
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    @PostMapping(value = "/{attrId}/values/{id}", produces = "application/json; charset=utf-8")
    public ProductAttrValue updateAttrValue(@ModelAttribute BeAttrValueForm form) throws BussException {
        return productAttrService.updateAttrValue(form);
    }

    /**
     * 删除 attrId 下的 value id
     *
     * @param form ProductAttr value delete form
     * @throws BussException ProductError.ATTR_NOT_FOUND
     */
    @PostMapping(value = "/{attrId}/values/{attrValueId}/delete", produces = "application/json; charset=utf-8")
    public void deleteAttrValue(@ModelAttribute BeAttrValueDeleteForm form) throws BussException {
        productAttrService.deleteAttrValue(form);
    }
}
