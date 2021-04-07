package cn.jimoos.provider.impl;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductMapper;
import cn.jimoos.dao.ProductSkuAttrMapMapper;
import cn.jimoos.dao.ProductSkuMapper;
import cn.jimoos.dto.ProductSkuForOrderDTO;
import cn.jimoos.model.Product;
import cn.jimoos.model.ProductSku;
import cn.jimoos.model.ProductSkuAttrMap;
import cn.jimoos.product.model.ProductItem;
import cn.jimoos.product.provider.IProductProvider;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/04/07 15:41
 */
@Slf4j
@Component
public class ProductProviderImpl implements IProductProvider {
    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    ProductSkuAttrMapMapper productSkuAttrMapMapper;

    @Resource
    ProductMapper productMapper;

    /**
     * 获取 商品明细
     *
     * @param skuId skuId
     * @return ProductItem
     */
    @Nullable
    @Override
    public ProductItem byId(Long skuId) throws BussException {
        if (skuId == null || skuId <= 0) {
            throw new BussException(ErrorCodeDefine.FORM_PARAMS_NOT_VALID);
        }

        ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);

        if (productSku == null) {
            return null;
        }

        Product product = productMapper.selectByPrimaryKey(productSku.getProductId());

        if (product == null) {
            return null;
        }
        List<ProductSkuAttrMap> skuAttrMap = productSkuAttrMapMapper.findBySkuId(skuId);

        //普通商品
        StringBuilder skuName = new StringBuilder();
        for (ProductSkuAttrMap productSkuAttrMap : skuAttrMap) {
            skuName.append(productSkuAttrMap.getAttrName()).append(":").append(productSkuAttrMap.getAttrValueName()).append(" ");
        }

        ProductItem productItem = new ProductItem();
        productItem.setProductId(productSku.getProductId());
        productItem.setProductType("NORMAL");
        productItem.setSkuId(productSku.getId());
        productItem.setPrice(productSku.getPrice());
        productItem.setSkuName(skuName.toString());
        productItem.setSkuCover(productSku.getCover());

        productItem.setProductName(product.getName());
        productItem.setProductCover(product.getCover());

        productItem.setARate(0);
        productItem.setBRate(0);

        return productItem;
    }

    /**
     * 占用库存
     *
     * @param skuId  skuId
     * @param number number
     */
    @Override
    public void occupyStock(Long skuId, int number) {
        throw new UnsupportedOperationException();
    }

    /**
     * 释放库存
     *
     * @param skuId  skuId
     * @param number number
     * @throws BussException
     */
    @Override
    public void unOccupyStock(Long skuId, int number) throws BussException {
        throw new UnsupportedOperationException();
    }

    /**
     * 批量获取 商品明细
     *
     * @param skuIds skuId
     * @return skuId
     */
    @Override
    public List<ProductItem> byIds(List<Long> skuIds) {
        if (!CollectionUtils.isEmpty(skuIds)) {
            List<ProductSkuForOrderDTO> dtoList = productSkuMapper.findDTOByIds(skuIds);

            List<ProductSkuAttrMap> skuAttrMap = productSkuAttrMapMapper.findBySkuIdIn(skuIds);
            Map<Long, List<ProductSkuAttrMap>> longListMap = skuAttrMap.parallelStream().collect(Collectors.groupingBy(ProductSkuAttrMap::getSkuId, Collectors.toList()));

            return dtoList.parallelStream().sequential().map(
                    r -> {
                        ProductItem productItem = new ProductItem();
                        productItem.setProductId(r.getProductId());
                        productItem.setProductType("NORMAL");
                        productItem.setProductName(r.getProductName());
                        productItem.setProductCover(r.getProductCover());
                        productItem.setSkuId(r.getSkuId());
                        List<ProductSkuAttrMap> productSkuAttrMaps = longListMap.get(r.getSkuId());
                        StringBuilder skuName = new StringBuilder();
                        for (ProductSkuAttrMap productSkuAttrMap : productSkuAttrMaps) {
                            skuName.append(productSkuAttrMap.getAttrName()).append(":").append(productSkuAttrMap.getAttrValueName()).append(" ");
                        }
                        productItem.setSkuName(skuName.toString());
                        productItem.setSkuCover(r.getSkuCover());
                        productItem.setPrice(r.getPrice());
                        productItem.setARate(0);
                        productItem.setBRate(0);
                        return productItem;
                    }
            ).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
