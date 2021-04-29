package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.*;
import cn.jimoos.model.Collection;
import cn.jimoos.model.RCollectionProduct;
import cn.jimoos.utils.http.Page;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:40.
 */
public interface CollectionService {
    /**
     * 添加 商品集合
     *
     * @param form 表单
     * @return Collection Object
     */
    Collection addCollection(BeCollectionForm form);

    /**
     * 更新 集合信息
     *
     * @param form 集合信息
     * @return Collection
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    Collection updateCollection(BeCollectionForm form) throws BussException;

    /**
     * 查询集合信息
     *
     * @param collectionId 集合 ID
     * @return Collection
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    Collection findById(Long collectionId) throws BussException;

    /**
     * back-end collection query
     *
     * @param beCollectionQueryForm form
     * @return Page<Collection>
     */
    Page<Collection> beQuery(BeCollectionQueryForm beCollectionQueryForm);

    /**
     * 上架
     *
     * @param form 表单
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    void upCollection(BeCollectionIdForm form) throws BussException;

    /**
     * 下架
     *
     * @param form 表单
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    void downCollection(BeCollectionIdForm form) throws BussException;

    /**
     * 推荐
     *
     * @param form 表单
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    void recommendCollection(BeCollectionIdForm form) throws BussException;

    /**
     * 取消推荐
     *
     * @param form 表单
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    void unRecommendCollection(BeCollectionIdForm form) throws BussException;

    /**
     * 删除 集合
     *
     * @param beCollectionIdForm 表单
     * @throws BussException CollectionError.COLLECTION_NOT_FOUND
     */
    void deleteCollection(BeCollectionIdForm beCollectionIdForm) throws BussException;

    Page<RCollectionProduct> findByCollectionIdPage(BeCollectionIdForm beCollectionIdForm);

    int addRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm);

    int updateRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm);

    int batchUpdateRCollectionProducts(BeBatchUpdateForm beBatchUpdateForm);

    int removeRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm);
}
