package cn.jimoos.factory;

import cn.jimoos.entity.CollectionEntity;
import cn.jimoos.form.BeCollectionForm;
import cn.jimoos.repository.CollectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:24.
 */
@Component
public class CollectionFactory {
    @Resource
    CollectionRepository collectionRepository;

    public CollectionEntity create(BeCollectionForm beCollectionForm) {
        CollectionEntity collectionEntity = new CollectionEntity(collectionRepository);
        BeanUtils.copyProperties(beCollectionForm, collectionEntity);
        collectionEntity.setStatus(false);
        collectionEntity.setDeleted(false);
        collectionEntity.setCreateAt(System.currentTimeMillis());
        return collectionEntity;
    }
}
