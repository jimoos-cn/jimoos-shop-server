package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductTagMapper;
import cn.jimoos.dao.RProductTagMapper;
import cn.jimoos.dto.ProductTagDto;
import cn.jimoos.entity.ProductTagEntity;
import cn.jimoos.entity.RProductTagEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.model.ProductTag;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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

    /**
     * 插入或更改 商品标签绑定值
     *
     * @param rProductTagEntity
     */
    public boolean saveBoundValue(RProductTagEntity rProductTagEntity){
        int i = 0;
        if (rProductTagEntity.getId() == null) {
            i = rProductTagMapper.insert(rProductTagEntity);
        }else{
            i = rProductTagMapper.updateByPrimaryKey(rProductTagEntity);
        }
        Assert.isTrue(i > 0, "新增或修改失败");
        return true;
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

    /**
     * 删除某商品绑定的标签值
     *
     * @param productTagEntity 标签ID
     */
    public boolean deleteBoundValue(RProductTagEntity productTagEntity) {
        int i = rProductTagMapper.deleteByTagIdAndProductId(productTagEntity);
        Assert.isTrue(i > 0, "删除失败");
        return true;
    }

    /**
     * 查询某商品绑定的值
     * @param rProductTagEntity
     * @return
     */
    public List<ProductTagDto> queryBoundValue(RProductTagEntity rProductTagEntity) {
        return productTagMapper.findByProductIdIn(Collections.singleton(rProductTagEntity.getProductId()));
    }

    /**
     * 查询某商品未绑定的值
     * @param rProductTagEntity
     * @return
     */
    public List<ProductTagDto> queryUnBoundValue(RProductTagEntity rProductTagEntity) {
        return productTagMapper.findByProductIdNotIn(rProductTagEntity.getProductId());
    }
}
