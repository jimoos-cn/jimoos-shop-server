package cn.jimoos.entity;

import cn.jimoos.model.ProductSku;
import cn.jimoos.model.ProductSkuAttrMap;
import cn.jimoos.repository.ProductSkuRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
