package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.*;
import cn.jimoos.entity.ProductEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:44.
 */
@Repository
public class ProductRepository {
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductCategoryMapper productCategoryMapper;
    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    RProductTagMapper rProductTagMapper;
    @Resource
    ProductTagMapper productTagMapper;
    @Resource
    ProductSkuAttrMapMapper productSkuAttrMapMapper;

    /**
     * 保存 ProductEntity信息
     *
     * @param productEntity product entity
     */
    public void save(ProductEntity productEntity) {
        if (productEntity.getId() != null && productEntity.getId() > 0) {
            productMapper.updateByPrimaryKey(productEntity);

            //移除 tag 关联
            rProductTagMapper.deleteByProductId(productEntity.getId());
            List<RProductTag> rProductTags = productEntity.getRProductTagInputs();
            if (!CollectionUtils.isEmpty(rProductTags)) {
                rProductTagMapper.batchInsert(rProductTags);
            }
        } else {
            productMapper.insert(productEntity);

            List<RProductTag> rProductTags = productEntity.getRProductTagInputs();
            if (!CollectionUtils.isEmpty(rProductTags)) {
                rProductTags = rProductTags.stream().peek(rProductTag -> rProductTag.setProductId(productEntity.getId())).collect(Collectors.toList());
                rProductTagMapper.batchInsert(rProductTags);
            }
        }
    }

    /**
     * 保存 ProductEntity信息 此处不修改时不删除tag
     * 2021年7月22日16:35:41
     * @param productEntity product entity
     */
    public void saveAndNoDelete(ProductEntity productEntity) {
        if (productEntity.getId() != null && productEntity.getId() > 0) {
            productMapper.updateByPrimaryKey(productEntity);
        } else {
            productMapper.insert(productEntity);
            List<RProductTag> rProductTags = productEntity.getRProductTagInputs();
            if (!CollectionUtils.isEmpty(rProductTags)) {
                rProductTags = rProductTags.stream().peek(rProductTag -> rProductTag.setProductId(productEntity.getId())).collect(Collectors.toList());
                rProductTagMapper.batchInsert(rProductTags);
            }
        }
    }

    /**
     * 查找 product 对象
     *
     * @param id product id
     * @return ProductEntity
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    public ProductEntity byId(Long id) throws BussException {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new BussException(ProductError.PRODUCT_NOT_EXIST);
        }
        return wrapper(product, false);
    }

    /**
     * Product的 entity wrapper方法
     *
     * @param product  product
     * @param skipRepo skip repo inject
     */
    private ProductEntity wrapper(Product product, boolean skipRepo) {
        if (product != null) {
            ProductEntity productEntity;
            if (skipRepo) {
                productEntity = new ProductEntity();
            } else {
                productEntity = new ProductEntity(this);
            }
            BeanUtils.copyProperties(product, productEntity);
            return productEntity;
        }
        return null;
    }

    /**
     * 保存 SKUs 信息,删除原有的 再更新。
     *
     * @param productEntity Product Entity
     */
    public void saveSkus(ProductEntity productEntity) {
        if (productEntity != null) {
            productSkuMapper.updateDeletedByProductId(Boolean.TRUE, productEntity.getId());
            List<ProductEntity.SkuEntity> skuEntities = productEntity.getProductSkuInputs();
            if (CollectionUtils.isEmpty(skuEntities)) {
                return;
            }
            productSkuMapper.batchInsert(skuEntities.stream().map(ProductSku.class::cast).collect(Collectors.toList()));

            List<ProductSku> productSkus = productEntity.getProductSkus();
            Map<String, ProductSku> idToProductSkuMap = productSkus.stream().collect(Collectors.toMap(ProductSku::getAttrValueIds, Function.identity()));

            List<ProductSkuAttrMap> productSkuAttrMaps = new ArrayList<>();

            skuEntities.forEach(skuEntity -> {
                ProductSku productSku = idToProductSkuMap.get(skuEntity.getBindAttrValueIds());
                //批量更新 sku 下的 attr map
                if (productSku != null) {
                    List<ProductSkuAttrMap> skuAttrMaps = skuEntity.getSkuAttrMaps();
                    productSkuAttrMaps.addAll(skuAttrMaps.stream().peek(skuAttr -> skuAttr.setSkuId(productSku.getId())).collect(Collectors.toList()));
                }
            });

            if (!CollectionUtils.isEmpty(productSkuAttrMaps)) {
                productSkuAttrMapMapper.batchInsert(productSkuAttrMaps);
            }
        }
    }

    /**
     * 删除商品
     *
     * @param productEntity
     */
    public void delete(ProductEntity productEntity) {
        productMapper.updateByPrimaryKey(productEntity);
        productSkuMapper.updateDeletedByProductId(Boolean.TRUE, productEntity.getId());
    }

    /**
     * find tags by product Id
     *
     * @param productId product Id
     * @return List<ProductTag>
     */
    public List<ProductTag> findTagsByProductId(Long productId) {
        List<RProductTag> rProductTags = rProductTagMapper.findByProductId(productId);

        if (CollectionUtils.isEmpty(rProductTags)) {
            return new ArrayList<>();
        }

        List<Long> tagIds = rProductTags.stream().map(RProductTag::getTagId).collect(Collectors.toList());
        return productTagMapper.findByIdIn(tagIds);
    }

    /**
     * find category by cate id
     *
     * @param cateId category id
     * @return ProductCategory
     */
    public ProductCategory findCategoryByCateId(Long cateId) {
        return productCategoryMapper.selectByPrimaryKey(cateId);
    }

    /**
     * 查询商品的 SKU 列表
     *
     * @param id product id
     * @return List<ProductSku>
     */
    public List<ProductSku> findSkusById(Long id) {
        return productSkuMapper.findByProductId(id);
    }

    /**
     * 更新某个 SKU 的信息，无法修改 attr map
     *
     * @param skuEntity sku Entity
     */
    public void updateOneSku(ProductEntity.SkuEntity skuEntity) {
        ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuEntity.getId());

        if (productSku != null) {
            productSku.setCover(skuEntity.getCover());
            productSku.setPrice(skuEntity.getPrice());
            productSku.setShowPrice(skuEntity.getShowPrice());
            productSku.setUpdateAt(System.currentTimeMillis());
            productSkuMapper.updateByPrimaryKey(productSku);
        }
    }

    /**
     * 根据 skuId 获取 列表
     *
     * @param skuId sku Id
     * @return List<ProductSkuAttrMap>
     */
    public List<ProductSkuAttrMap> findAttrMapBySkuId(Long skuId) {
        return productSkuAttrMapMapper.findBySkuId(skuId);
    }

    /**
     * 根据 skuId 获取 列表
     *
     * @param skuIds sku Id collection
     * @return List<ProductSkuAttrMap>
     */
    public List<ProductSkuAttrMap> findAttrMapsBySkuIds(Collection<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) {
            return new ArrayList<>();
        }
        return productSkuAttrMapMapper.findBySkuIdIn(skuIds);
    }

    /**
     * 至少存在一个 sku
     *
     * @param productId product Id
     * @return true 有 SKU  false 无 SKU
     */
    public boolean hasAnySkus(Long productId) {
        return productSkuMapper.findAnyOneByProductId(productId) != null;
    }

    /**
     * 查询 商品的  最低价 sku
     *
     * @param productId product Id
     * @return ProductSku
     */
    public ProductSku findMinPriceSku(Long productId) {
        return productSkuMapper.findMinPriceByProductId(productId);
    }
}
