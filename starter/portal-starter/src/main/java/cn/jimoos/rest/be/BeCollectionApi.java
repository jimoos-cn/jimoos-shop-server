package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.*;
import cn.jimoos.model.Collection;
import cn.jimoos.service.CollectionService;
import cn.jimoos.utils.http.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品集合管理
 *
 * @author :keepcleargas
 * @date :2021-04-29 20:28.
 */
@RestController
@RequestMapping("/bAdmin/v1/collections")
public class BeCollectionApi {
    @Resource
    CollectionService collectionService;

    /**
     * 查询列表
     *
     * @param form Collection Query Form
     * @return List<Collection>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<Collection> query(@ModelAttribute BeCollectionQueryForm form) {
        return collectionService.beQuery(form);
    }

    /**
     * 添加商品集合
     *
     * @param form collection Form
     * @return Collection
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public Collection add(@ModelAttribute BeCollectionForm form) {
        return collectionService.addCollection(form);
    }

    /**
     * 获取商品集合
     *
     * @param id collectionId
     * @return Collection
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Collection getOne(@PathVariable("id") Long id) throws BussException {
        return collectionService.findById(id);
    }

    /**
     * 更新商品集合
     *
     * @param form collection Update Form
     * @return Collection
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Collection update(@ModelAttribute BeCollectionForm form) throws BussException {
        return collectionService.updateCollection(form);
    }


    /**
     * 删除商品集合
     *
     * @param deleteForm collection delete form
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}/delete", produces = "application/json; charset=utf-8")
    public void deleteCollection(@ModelAttribute BeCollectionIdForm deleteForm) throws BussException {
        collectionService.deleteCollection(deleteForm);
    }

    /**
     * 上架
     *
     * @param upForm collection  up form
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}/up", produces = "application/json; charset=utf-8")
    public void upCollection(@ModelAttribute BeCollectionIdForm upForm) throws BussException {
        collectionService.upCollection(upForm);
    }

    /**
     * 下架
     *
     * @param downForm collection down form
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}/down", produces = "application/json; charset=utf-8")
    public void downCollection(@ModelAttribute BeCollectionIdForm downForm) throws BussException {
        collectionService.downCollection(downForm);
    }


    /**
     * 推荐
     *
     * @param recommendForm collection  recommend form
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}/recommend", produces = "application/json; charset=utf-8")
    public void recommendCollection(@ModelAttribute BeCollectionIdForm recommendForm) throws BussException {
        collectionService.recommendCollection(recommendForm);
    }


    /**
     * 取消推荐
     *
     * @param form collection  unRecommend form
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    @PostMapping(value = "/{id}/cancelRecommend", produces = "application/json; charset=utf-8")
    public void cancelRecommend(@ModelAttribute BeCollectionIdForm form) throws BussException {
        collectionService.unRecommendCollection(form);
    }


    /**
     * 添加商品到集合
     *
     * @param form add product to collection form
     */
    @PostMapping(value = "/{id}/products", produces = "application/json; charset=utf-8")
    public void addProduct(@ModelAttribute BeRCollectionProductForm form) {
        collectionService.addRCollectionProduct(form);
    }


    /**
     * 更新
     *
     * @param form add product to collection form
     */
    @PostMapping(value = "/{id}/products/info", produces = "application/json; charset=utf-8")
    public void updateProductInfo(@ModelAttribute BeRCollectionProductForm form) {
        collectionService.updateRCollectionProduct(form);
    }


    /**
     * 批量更新
     *
     * @param form add product to collection form
     */
    @PostMapping(value = "/{collectionId}/products/batch", produces = "application/json; charset=utf-8")
    public void batchUpdate(@RequestBody BeBatchUpdateForm form) {
        collectionService.batchUpdateRCollectionProducts(form);
    }


    /**
     *  移除商品
     *
     * @param form add product to collection form
     */
    @PostMapping(value = "/{collectionId}/products/delete", produces = "application/json; charset=utf-8")
    public void remove(@RequestBody BeRCollectionProductForm form) {
        collectionService.removeRCollectionProduct(form);
    }
}
