package cn.jimoos.service.impl;

import cn.jimoos.dao.UserProductCollectionMapper;
import cn.jimoos.model.UserProductCollection;
import cn.jimoos.service.ProductCollectService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-02 13:51.
 */
@Service
public class ProductCollectServiceImpl implements ProductCollectService {
    @Resource
    UserProductCollectionMapper userProductCollectionMapper;

    @Override
    public void collect(Long userId, Long productId) {
        UserProductCollection userProductCollection = userProductCollectionMapper.findOneByUserIdAndProductId(userId, productId);

        if (userProductCollection != null) {
            userProductCollection = new UserProductCollection();
            userProductCollection.setProductId(productId);
            userProductCollection.setUserId(userId);
            userProductCollection.setCreateAt(System.currentTimeMillis());
            userProductCollectionMapper.insert(userProductCollection);
        }
    }

    @Override
    public void unCollect(Long userId, Long productId) {
        userProductCollectionMapper.deleteByUserIdAndProductId(userId, productId);
    }

    @Nullable
    @Override
    public UserProductCollection findByUserIdAndProductId(Long userId, Long productId) {
        return userProductCollectionMapper.findOneByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<UserProductCollection> findByUserIdAndProductIdIn(Long userId, Collection<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return new ArrayList<>();
        }
        return userProductCollectionMapper.findByUserIdAndProductIdIn(userId, productIds);
    }
}
