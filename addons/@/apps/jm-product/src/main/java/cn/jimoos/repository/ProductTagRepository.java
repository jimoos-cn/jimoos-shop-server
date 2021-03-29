package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductTagMapper;
import cn.jimoos.dao.RProductTagMapper;
import cn.jimoos.entity.ProductTagEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.model.ProductTag;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:52.
 */
@Repository
public class ProductTagRepository {
    @Resource
    ProductTagMapper productTagMapper;
    @Resource
    RProductTagMapper rProductTagMapper;

    @Nullable
    public ProductTagEntity byId(Long id) throws BussException {
        ProductTag productTag = productTagMapper.selectByPrimaryKey(id);
        if (productTag == null) {
            throw new BussException(ProductError.TAG_NOT_FOUND);
        }
        ProductTagEntity productTagEntity = new ProductTagEntity(this);
        BeanUtils.copyProperties(productTag, productTagEntity);
        return productTagEntity;
    }

    public void save(ProductTagEntity productTagEntity) {
        if (productTagEntity.getId() == null) {
            productTagMapper.insert(productTagEntity);
        } else {
            productTagMapper.updateByPrimaryKey(productTagEntity);
        }
    }

    public int delete(Long id) {
        return productTagMapper.updateDeletedById(Boolean.TRUE, id);
    }

    /**
     * 查询绑定 的商品数目
     */
    public Long countProductNumByTagId(Long id) {
        return rProductTagMapper.countByTagId(id);
    }
}
