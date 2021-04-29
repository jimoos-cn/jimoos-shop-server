package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.CollectionMapper;
import cn.jimoos.dao.RCollectionProductMapper;
import cn.jimoos.entity.CollectionEntity;
import cn.jimoos.error.CollectionError;
import cn.jimoos.factory.CollectionFactory;
import cn.jimoos.form.*;
import cn.jimoos.model.Collection;
import cn.jimoos.model.RCollectionProduct;
import cn.jimoos.repository.CollectionRepository;
import cn.jimoos.service.CollectionService;
import cn.jimoos.utils.http.Page;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-29 15:26.
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    CollectionRepository collectionRepository;
    @Resource
    CollectionFactory collectionFactory;
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    RCollectionProductMapper rCollectionProductMapper;

    @Override
    public Collection addCollection(BeCollectionForm form) {
        CollectionEntity collectionEntity = collectionFactory.create(form);
        collectionRepository.save(collectionEntity);
        return collectionEntity;
    }

    @Override
    public Collection updateCollection(BeCollectionForm form) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(form.getId());

        collectionEntity.update(form);
        collectionRepository.save(collectionEntity);
        return collectionEntity;
    }

    @Override
    public Collection findById(Long collectionId) throws BussException {
        return findByIdThrow(collectionId);
    }

    @Override
    public Page<Collection> beQuery(BeCollectionQueryForm form) {
        long count = collectionMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            return Page.create(count, collectionMapper.queryTable(form.toQueryMap()));
        }
        return Page.empty();
    }

    @Override
    public void upCollection(BeCollectionIdForm form) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(form.getId());
        collectionEntity.up();

        collectionRepository.save(collectionEntity);
    }

    @Override
    public void downCollection(BeCollectionIdForm form) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(form.getId());
        collectionEntity.down();

        collectionRepository.save(collectionEntity);
    }

    @Override
    public void recommendCollection(BeCollectionIdForm beCollectionIdForm) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(beCollectionIdForm.getId());
        collectionEntity.recommend();

        collectionRepository.save(collectionEntity);
    }

    @Override
    public void unRecommendCollection(BeCollectionIdForm beCollectionIdForm) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(beCollectionIdForm.getId());
        collectionEntity.unRecommend();

        collectionRepository.save(collectionEntity);
    }

    @Override
    public void deleteCollection(BeCollectionIdForm beCollectionIdForm) throws BussException {
        CollectionEntity collectionEntity = findByIdThrow(beCollectionIdForm.getId());
        collectionEntity.softDelete();

        collectionRepository.save(collectionEntity);
    }

    @Override
    public Page<RCollectionProduct> findByCollectionIdPage(BeCollectionIdForm beCollectionIdForm) {
        Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(3);
        queryMap.put("offset", beCollectionIdForm.getOffset());
        queryMap.put("limit", beCollectionIdForm.getLimit());
        queryMap.put("collectionId", beCollectionIdForm.getId());
        long count = rCollectionProductMapper.queryTableCount(queryMap);

        if (count > 0) {
            return Page.create(count, rCollectionProductMapper.queryTable(queryMap));
        }
        return Page.empty();
    }

    @Override
    public int addRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm) {
        return 0;
    }

    @Override
    public int updateRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm) {
        return 0;
    }

    @Override
    public int batchUpdateRCollectionProducts(BeBatchUpdateForm beBatchUpdateForm) {
        return 0;
    }

    @Override
    public int removeRCollectionProduct(BeRCollectionProductForm beRCollectionProductForm) {
        return 0;
    }

    private CollectionEntity findByIdThrow(Long id) throws BussException {
        CollectionEntity collectionEntity = collectionRepository.findById(id);

        if (collectionEntity == null) {
            throw new BussException(CollectionError.COLLECTION_NOT_FOUND);
        }
        return collectionEntity;
    }
}
