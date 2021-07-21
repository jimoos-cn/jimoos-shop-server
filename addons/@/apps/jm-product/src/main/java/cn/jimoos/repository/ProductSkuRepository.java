package cn.jimoos.repository;

import cn.jimoos.dao.ProductSkuAttrMapMapper;
import cn.jimoos.dao.ProductSkuMapper;
import cn.jimoos.entity.ProductSkuAttrMapEntity;
import cn.jimoos.entity.ProductSkuEntity;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/7/20 11:43:36
 * @description
 */
@Repository
public class ProductSkuRepository {
    @Resource
    ProductSkuMapper productSkuMapper;

    @Resource
    ProductSkuAttrMapMapper productSkuAttrMapMapper;

    /**
     * 保存或修改
     *
     * @param productSkuEntity
     * @return
     */
    public boolean save(ProductSkuEntity productSkuEntity) {
        int i = 0;
        if (productSkuEntity.getId() == null) {
            // 创建时间
            productSkuEntity.create();
            i = productSkuMapper.insert(productSkuEntity);
            productSkuEntity.forAttrInsert();
            // 批量插入关系表
            if (productSkuEntity.getAttrs() != null) {
                productSkuAttrMapMapper.batchInsert(productSkuEntity.getAttrs());
            }
        }else{
            // 设置更新时间
            productSkuEntity.update();
            i = productSkuMapper.updateById(productSkuEntity);
            // 批量更新
            if (productSkuEntity.getAttrs() != null) {
                productSkuAttrMapMapper.batchUpdate(productSkuEntity.getAttrs());
            }
        }
        Assert.isTrue(i > 0, "ProductSku 新增或修改失败");
        return true;
    }

    /**
     * 将原来的数据先进行假删除
     * @param productId
     * @return
     */
    public boolean deleteByProductId(Long productId) {
        productSkuMapper.updateDeletedByProductId(true, productId);
        return true;
    }

    /**
     * 根据skuid 软删除关系冗余表
     *
     * @param id
     */
    public boolean deleteAttrBySkuId(Long id) {
        ProductSkuAttrMapEntity param = new ProductSkuAttrMapEntity();
        param.delete();
        int i = productSkuAttrMapMapper.updateBySkuId(param, id);
        Assert.isTrue(i > 0,"ProductSkuAttrMap 更新失败");
        return true;
    }
}
