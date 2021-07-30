package cn.jimoos.entity;

import cn.jimoos.form.product.BeProductForm;
import cn.jimoos.model.*;
import cn.jimoos.repository.ProductRepository;
import cn.jimoos.vo.ProductSkuVO;
import cn.jimoos.vo.ProductVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductEntity extends Product {
    private ProductRepository productRepository;
    private List<RProductTag> rProductTagInputs = new ArrayList<>();
    private List<ProductSkuEntity> productSkuInputs = new ArrayList<>();
    private UserProductCollection userProductCollectionInput;

    public ProductEntity(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 绑定标签
     *
     * @param tagIds tag id list
     */
    public void attachTags(List<Long> tagIds) {
        if (!CollectionUtils.isEmpty(tagIds)) {
            long now = System.currentTimeMillis();
            rProductTagInputs.addAll(tagIds.stream().map(
                    tagId -> {
                        RProductTag rProductTag = new RProductTag();
                        rProductTag.setProductId(this.getId());
                        rProductTag.setTagId(tagId);
                        rProductTag.setCreateAt(now);
                        return rProductTag;
                    }
            ).collect(Collectors.toList()));
        }
    }

    /**
     * 查询 商品标签
     *
     * @return List<ProductTag>
     */
    public List<ProductTag> getTags() {
        return productRepository.findTagsByProductId(this.getId());
    }

    /**
     * 查询商品类别
     *
     * @return ProductCategory
     */
    public ProductCategory getCategory() {
        return productRepository.findCategoryByCateId(this.getCategoryId());
    }

    /**
     * 查询商品的 Sku 列表
     *
     * @return List<ProductSku>
     */
    public List<ProductSku> getProductSkus() {
        return productRepository.findSkusById(this.getId());
    }

    /**
     * 获取商品的 SkuVo 列表
     *
     * @return List<ProductSkuVO>
     */
    public List<ProductSkuVO> getProductSkuVos() {
        List<ProductSku> productSkus = getProductSkus();
        if (CollectionUtils.isEmpty(productSkus)) {
            return new ArrayList<>();
        }

        List<Long> skuIds = productSkus.stream().map(ProductSku::getId).collect(Collectors.toList());
        List<ProductSkuAttrMap> skuAttrMaps = productRepository.findAttrMapsBySkuIds(skuIds);
        Map<Long, List<ProductSkuAttrMap>>
                skuIdToListMap = skuAttrMaps.stream().collect(Collectors.groupingBy(ProductSkuAttrMap::getSkuId));

        return productSkus.stream().map(productSku -> {
            ProductSkuVO productSkuVO = new ProductSkuVO();
            BeanUtils.copyProperties(productSku, productSkuVO);
            productSkuVO.setAttrs(skuIdToListMap.get(productSku.getId()));
            return productSkuVO;
        }).collect(Collectors.toList());
    }

    /**
     * 添加 Sku 列表
     *
     * @param skuInputs sku input forms
     */
    public void addSkus(List<BeProductForm.SkuInput> skuInputs) {
        productSkuInputs.addAll(skuInputs.stream().map(skuInput -> {
            ProductSkuEntity productSku = new ProductSkuEntity(this, skuInput);
            productSku.addAttrMaps(skuInput.getAttrs());
            return productSku;
        }).collect(Collectors.toList()));
    }

    /**
     * 软删除
     */
    public void softDelete() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 上架
     */
    public void up() {
        this.setStatus(Status.LISTED.val());
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 下架
     */
    public void down(String reason) {
        this.setStatus(Status.NOT_LISTED.val());
        this.setUpdateAt(System.currentTimeMillis());
        //todo 理由
    }

    /**
     * 是否有 SKU
     *
     * @return true 有 SKU ,false 没有 SKU
     */
    public boolean hasAnySkus() {
        return productRepository.hasAnySkus(this.getId());
    }

    /**
     * 更新商品信息
     *
     * @param form product form
     */
    public void updateInfo(BeProductForm form) {
        Long now = System.currentTimeMillis();
        // add 2021年7月22日16:39:25 补充修改商品类别
        this.setCategoryId(form.getCategoryId());
        this.setName(form.getName());
        this.setText(form.getText());
        this.setCover(form.getCover());
        this.setVideoUrl(form.getVideoUrl());
        this.setBannerUrls(form.getBannerUrls());
        this.setSort(form.getSort());
        this.setFakeSales(form.getFakeSales());
        this.setMerchantId(form.getMerchantId());
        this.setCategoryId(form.getCategoryId());
        this.setStatus(form.getStatus());
        this.setUpdateAt(now);
    }


    public enum Status {
        /**
         * 未上架
         */
        NOT_LISTED((byte) 0),

        /**
         * 上架
         */
        LISTED((byte) 2);

        private final byte val;

        Status(byte val) {
            this.val = val;
        }

        public byte val() {
            return val;
        }
    }

    public enum Type {

        /**
         * 普通商品
         */
        NORMAL((byte) 0);

        private final byte val;

        Type(byte val) {
            this.val = val;
        }

        public byte val() {
            return val;
        }
    }

    /**
     * 单个返回的使用，批量 禁止使用
     *
     * @return ProductVO
     */
    public ProductVO toVO() {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(this, productVO);
        ProductSku productSku = productRepository.findMinPriceSku(this.getId());
        if (productSku == null) {
            productVO.setPrice(BigDecimal.ZERO);
            productVO.setShowPrice(BigDecimal.ZERO);
        } else {
            productVO.setPrice(productSku.getPrice());
            productVO.setShowPrice(productSku.getShowPrice());
        }
        return productVO;
    }
}
