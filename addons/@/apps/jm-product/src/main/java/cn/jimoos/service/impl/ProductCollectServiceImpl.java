package cn.jimoos.service.impl;

import cn.jimoos.dao.ProductMapper;
import cn.jimoos.dao.ProductSkuMapper;
import cn.jimoos.dao.UserProductCollectionMapper;
import cn.jimoos.model.Product;
import cn.jimoos.model.ProductSku;
import cn.jimoos.model.UserProductCollection;
import cn.jimoos.service.ProductCollectService;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import cn.jimoos.vo.ProductCollectVO;
import cn.jimoos.vo.ProductSimpleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-04-02 13:51.
 */
@Service
public class ProductCollectServiceImpl implements ProductCollectService {
    @Resource
    UserProductCollectionMapper userProductCollectionMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductSkuMapper productSkuMapper;

    @Override
    public void collect(Long userId, Long productId) {
        UserProductCollection userProductCollection = userProductCollectionMapper.findOneByUserIdAndProductId(userId, productId);

        if (userProductCollection == null) {
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

    @Override
    public List<ProductCollectVO> getUserCollects(AbstractUserPageForm4L abstractUserPageForm4L) {
        List<UserProductCollection> userProductCollections = userProductCollectionMapper.findByUserId(abstractUserPageForm4L.getUserId(), abstractUserPageForm4L.getOffset(), abstractUserPageForm4L.getLimit());

        if (CollectionUtils.isEmpty(userProductCollections)) {
            return new ArrayList<>();
        } else {
            List<Long> productIds = userProductCollections.stream().map(UserProductCollection::getProductId).collect(Collectors.toList());
            List<Product> products = productMapper.findByIdIn(productIds);
            Map<Long, Product> idToProductMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

            List<ProductSku> minPriceSkus = productSkuMapper.findMinPricesByProductIds(productIds);

            Map<Long, ProductSku> idToProductSkuMap = minPriceSkus.stream().collect(Collectors.toMap(ProductSku::getProductId, Function.identity()));

            return userProductCollections.stream().map(userProductCollection -> {
                ProductCollectVO productCollectVO = new ProductCollectVO();
                BeanUtils.copyProperties(userProductCollection, productCollectVO);
                Product product = idToProductMap.get(userProductCollection.getProductId());
                if (product != null) {
                    ProductSimpleVO productSimpleVO = new ProductSimpleVO();
                    BeanUtils.copyProperties(product, productSimpleVO);
                    ProductSku productSku = idToProductSkuMap.get(product.getId());
                    if (productSku != null) {
                        productSimpleVO.setPrice(productSku.getPrice());
                        productSimpleVO.setShowPrice(productSku.getShowPrice());
                    }
                    productCollectVO.setProduct(productSimpleVO);
                } else {
                    productCollectVO.setProduct(null);
                }
                return productCollectVO;
            }).collect(Collectors.toList());
        }
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
