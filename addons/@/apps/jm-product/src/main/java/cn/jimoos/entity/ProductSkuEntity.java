package cn.jimoos.entity;

import cn.jimoos.form.product.BeProductForm;
import cn.jimoos.model.ProductSku;
import cn.jimoos.model.ProductSkuAttrMap;
import cn.jimoos.repository.ProductSkuRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SiletFlower
 * @date 2021/7/20 13:16:16
 * @description
 */
@Data
@NoArgsConstructor
public class ProductSkuEntity extends ProductSku{
    private ProductSkuRepository productSkuRepository;
    /**
     * 关联关系冗余表
     */
    List<ProductSkuAttrMap> attrs;

    public ProductSkuEntity(ProductSkuRepository productSkuRepository) {
        this.productSkuRepository = productSkuRepository;
    }

    public ProductSkuEntity(ProductEntity productEntity, BeProductForm.SkuInput skuInput) {
        long now = System.currentTimeMillis();
        this.setAttrValueIds("");
        this.setCover(skuInput.getCover());
        this.setPrice(skuInput.getPrice());
        this.setShowPrice(skuInput.getShowPrice());
        this.setProductId(productEntity.getId());
        this.setMerchantId(productEntity.getMerchantId());
        this.setCreateAt(now);
        this.setUpdateAt(now);
        this.setDeleted(false);
    }

    /**
     * 绑定 SKU 销售属性值
     *
     * @param attrs sku attr
     */
    public void addAttrMaps(List<BeProductForm.Attr> attrs) {
        if (this.attrs == null) {
            this.attrs = new ArrayList<ProductSkuAttrMap>();
        }
        this.attrs.addAll(attrs.stream().map(attr -> {
            ProductSkuAttrMap productSkuAttrMap = new ProductSkuAttrMap();
            productSkuAttrMap.setAttrId(attr.getAttrId());
            productSkuAttrMap.setAttrName(attr.getAttrName());
            productSkuAttrMap.setAttrValueName(attr.getAttrValueName());
            productSkuAttrMap.setAttrValueId(attr.getAttrValueId());
            productSkuAttrMap.setMerchantId(this.getMerchantId());
            productSkuAttrMap.setProductId(this.getProductId());
            productSkuAttrMap.setCreateAt(System.currentTimeMillis());
            productSkuAttrMap.setUpdateAt(0L);
            productSkuAttrMap.setDeleted(Boolean.FALSE);
            return productSkuAttrMap;
        }).collect(Collectors.toList()));
        //设置 bindAttrValueIds
        this.setAttrValueIds(getBindAttrValueIds());
    }

    /**
     * 获取 绑定 attrValueId
     *
     * @return String
     */
    public String getBindAttrValueIds() {
        List<Long> attrValueIds = this.getAttrs().stream().map(ProductSkuAttrMap::getAttrValueId).collect(Collectors.toList());
        return StringUtils.collectionToCommaDelimitedString(attrValueIds);
    }

// 状态相关操作
    /**
     * 设置删除
     */
    public void delete(){
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 设置更新
     */
    public void update(){
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 设置生成
     */
    public void create(){
        this.setCreateAt(System.currentTimeMillis());
    }

    /**
     * （Sku表初始）处理插入操作时对于数据库必填项的操作
     */
    public void forInsert(){
        if (this.getMerchantId() == null) {
            this.setMerchantId(0L);
        }
        if (this.getUpdateAt() == null) {
            this.setUpdateAt(0L);
        }
        if (this.getDeleted() == null) {
            this.setDeleted(true);
        }
    }

    /**
     * （Sku与Attr关系表）处理插入操作时对数据库必填项的操作
     */
    public void forAttrInsert(){
        this.attrs.forEach(attr->{
            attr.setSkuId(this.getId());
            attr.setProductId(this.getProductId());
            attr.setCreateAt(System.currentTimeMillis());
            if (attr.getUpdateAt() == null) {
                attr.setUpdateAt(0L);
            }
            if (attr.getDeleted() == null) {
                attr.setDeleted(false);
            }
            if(attr.getMerchantId() == null){
                attr.setMerchantId(0L);
            }
        });
    }



// Repository相关操作

    /**
     * 新增或修改
     */
    public boolean save(){
        return productSkuRepository.save(this);
    }

    /**
     * 将原来的数据先进行假删除
     *
     */
    public boolean deleteByProductId(){
        return productSkuRepository.deleteByProductId(this.getProductId());
    }

    /**
     * sku关系冗余表软删除
     */
    public boolean deleteAttr() {
        return productSkuRepository.deleteAttrBySkuId(this.getId());
    }
}
