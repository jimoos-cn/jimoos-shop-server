package cn.jimoos.repository;

import cn.jimoos.dao.ProductAttrMapper;
import cn.jimoos.dao.ProductAttrValueMapper;
import cn.jimoos.dao.ProductSkuAttrMapMapper;
import cn.jimoos.entity.ProductAttrEntity;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:37.
 */
@Repository
public class ProductAttrRepository {
    @Resource
    ProductAttrMapper productAttrMapper;
    @Resource
    ProductAttrValueMapper productAttrValueMapper;
    @Resource
    ProductSkuAttrMapMapper productSkuAttrMapMapper;


    /**
     * 根据 ID 获取 销售属性 ProductAttrEntity
     *
     * @param id Attr Id
     * @return ProductAttrEntity
     */
    @Nullable
    public ProductAttrEntity byId(Long id) {
        ProductAttr productAttr = productAttrMapper.selectByPrimaryKey(id);
        if (productAttr == null) {
            return null;
        }
        return wrapper(productAttr, false);
    }

    /**
     * ProductAttr的 entity wrapper方法
     *
     * @param productAttr 销售属性
     * @param skipRepo    跳过 Repo 注入
     * @return ProductAttrEntity
     */
    private ProductAttrEntity wrapper(ProductAttr productAttr, boolean skipRepo) {
        if (productAttr != null) {
            ProductAttrEntity productAttrEntity;
            if (skipRepo) {
                productAttrEntity = new ProductAttrEntity();
            } else {
                productAttrEntity = new ProductAttrEntity(this);
            }
            BeanUtils.copyProperties(productAttr, productAttrEntity);
            return productAttrEntity;
        }
        return null;
    }

    /**
     * 更具销售属性 ID 查询销售属性值列表
     *
     * @param id attr Id
     * @return List<ProductAttrValue>
     */
    public List<ProductAttrValue> findValuesById(Long id) {
        return productAttrValueMapper.findByAttrId(id);
    }

    /**
     * 删掉销售属性的同时删除销售属性值
     *
     * @param attrId id
     */
    public void delete(Long attrId) {
        productAttrMapper.updateDeletedById(Boolean.TRUE, attrId);
        productAttrValueMapper.updateDeletedByAttrId(Boolean.TRUE, attrId);
    }

    public void save(ProductAttrEntity productAttrEntity) {
        if (productAttrEntity.getId() == null) {
            productAttrMapper.insert(productAttrEntity);
            List<ProductAttrValue> productAttrValues = productAttrEntity.getAttrValueInputs();
            if (!CollectionUtils.isEmpty(productAttrValues)) {
                productAttrValues = productAttrValues.parallelStream().sequential().peek(attrValue -> attrValue.setAttrId(productAttrEntity.getId())).collect(Collectors.toList());
                productAttrValueMapper.batchInsert(productAttrValues);
            }
        } else {
            productAttrMapper.updateByPrimaryKey(productAttrEntity);
            productAttrValueMapper.updateDeletedByAttrId(Boolean.TRUE, productAttrEntity.getId());
            productAttrValueMapper.batchInsert(productAttrEntity.getAttrValues());
        }
    }

    /**
     * 属性 被使用
     *
     * @param id attr Id
     * @return if bind true,else false
     */
    public boolean hasAnyBindByAttrId(Long id) {
        return productSkuAttrMapMapper.findAnyOneByAttrId(id) != null;
    }

    /**
     * 属性值 被使用了
     *
     * @param valueId value id
     * @return if bind true,else false
     */
    public boolean hasAnyBindByAttrValueId(Long valueId) {
        return productSkuAttrMapMapper.findAnyOneByAttrValueId(valueId) != null;
    }
}
