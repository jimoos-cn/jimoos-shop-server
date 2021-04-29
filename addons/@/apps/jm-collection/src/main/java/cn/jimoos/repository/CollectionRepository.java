package cn.jimoos.repository;

import cn.jimoos.dao.CollectionMapper;
import cn.jimoos.dao.RCollectionProductMapper;
import cn.jimoos.entity.CollectionEntity;
import cn.jimoos.model.Collection;
import cn.jimoos.model.RCollectionProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:24.
 */
@Repository
public class CollectionRepository {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    RCollectionProductMapper collectionProductMapper;


    public CollectionEntity findById(Long collectionId) {
        return wrapper(collectionMapper.selectByPrimaryKey(collectionId), false);
    }

    /**
     * 保存 CollectionEntity信息
     *
     * @param collectionEntity 集合对象
     */
    public void save(CollectionEntity collectionEntity) {
        if (collectionEntity.getId() != null && collectionEntity.getId() > 0) {
            collectionMapper.updateByPrimaryKey(collectionEntity);
        } else {
            collectionMapper.insert(collectionEntity);
        }
    }

    /**
     * 查找 集合内 某个 商品
     *
     * @param id rCollectionProductId
     * @return RCollectionProduct
     */
    public RCollectionProduct findByRId(Long id) {
        return collectionProductMapper.selectByPrimaryKey(id);
    }

    /**
     * collection的 entity wrapper方法
     *
     * @param collection collection object
     * @param skipRepo   skip repo inject
     */
    private CollectionEntity wrapper(Collection collection, boolean skipRepo) {
        if (collection != null) {
            CollectionEntity collectionEntity;
            if (skipRepo) {
                collectionEntity = new CollectionEntity();
            } else {
                collectionEntity = new CollectionEntity(this);
            }
            BeanUtils.copyProperties(collection, collectionEntity);
            return collectionEntity;
        }
        return null;
    }

    /**
     * 保存商品
     *
     * @param collectionEntity 集合对象
     */
    public void saveProducts(CollectionEntity collectionEntity) {
        if (collectionEntity.isBatchUpdate()) {
            collectionProductMapper.deleteByCollectionId(collectionEntity.getId());
            if (!CollectionUtils.isEmpty(collectionEntity.getRCollectionProductInputs())) {
                collectionProductMapper.batchInsert(collectionEntity.getRCollectionProductInputs());
            }
        } else {
            if (!CollectionUtils.isEmpty(collectionEntity.getRCollectionProductInputs())) {
                RCollectionProduct rCollectionProduct = collectionEntity.getRCollectionProductInputs().get(0);
                if (rCollectionProduct.getId() != null && rCollectionProduct.getId() > 0) {
                    for (RCollectionProduct rCollectionProduct1 : collectionEntity.getRCollectionProductInputs()) {
                        collectionProductMapper.updateByPrimaryKey(rCollectionProduct1);
                    }
                } else {
                    collectionProductMapper.batchInsert(collectionEntity.getRCollectionProductInputs());
                }
            }
        }
    }

    /**
     * 删除内部 商品
     *
     * @param id collectionProductId
     */
    public void deleteRCollectionProduct(Long id) {
        collectionProductMapper.deleteById(id);
    }
}
