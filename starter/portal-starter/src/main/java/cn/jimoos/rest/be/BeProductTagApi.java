package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.tag.BeProductTagCreateForm;
import cn.jimoos.form.tag.BeProductTagDeleteForm;
import cn.jimoos.form.tag.BeProductTagSearchForm;
import cn.jimoos.form.tag.BeProductTagUpdateForm;
import cn.jimoos.model.ProductTag;
import cn.jimoos.service.ProductTagService;
import cn.jimoos.utils.http.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-30 12:06.
 */
@RestController
@RequestMapping("/bAdmin/v1/product/tags")
public class BeProductTagApi {
    @Resource
    ProductTagService productTagService;

    /**
     * 查询标签列表
     *
     * @param form Product TAG Search Form
     * @return List<ProductTag>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<ProductTag> query(@ModelAttribute BeProductTagSearchForm form) {
        return productTagService.query(form);
    }

    /**
     * 添加商品标签
     *
     * @param form Product TAG Form
     * @return ProductTag
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public ProductTag add(@ModelAttribute BeProductTagCreateForm form) {
        return productTagService.create(form);
    }

    /**
     * 更新商品标签
     *
     * @param form Product TAG Update Form
     * @return ProductTag
     * @throws BussException ProductError.TAG_NOT_FOUND
     */
    @PostMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ProductTag update(@ModelAttribute BeProductTagUpdateForm form) throws BussException {
        return productTagService.update(form);
    }


    /**
     * 删除 标签
     *
     * @param deleteForm product TAG delete form
     * @throws BussException ProductError.TAG_NOT_FOUND | ProductError.TAG_USED
     */
    @PostMapping(value = "/{tagId}/delete", produces = "application/json; charset=utf-8")
    public void deleteTag(@ModelAttribute BeProductTagDeleteForm deleteForm) throws BussException {
        productTagService.delete(deleteForm);
    }
}
