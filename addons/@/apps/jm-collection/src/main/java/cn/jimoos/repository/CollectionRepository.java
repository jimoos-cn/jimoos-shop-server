package cn.jimoos.repository;

import cn.jimoos.dao.CollectionMapper;
import cn.jimoos.dao.RCollectionProductMapper;
import cn.jimoos.entity.CollectionEntity;
import cn.jimoos.model.Collection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

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
     * @param collectionEntity
     */
    public void save(CollectionEntity collectionEntity) {
        if (collectionEntity.getId() != null && collectionEntity.getId() > 0) {
            collectionMapper.updateByPrimaryKey(collectionEntity);
        } else {
            collectionMapper.insert(collectionEntity);
        }
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
}
