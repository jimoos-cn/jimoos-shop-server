package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductCategoryMapper;
import cn.jimoos.dao.ProductMapper;
import cn.jimoos.entity.ProductCategoryEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.model.ProductCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @date 2021-03-29 19:52.
 */
@Repository
public class ProductCategoryRepository {
    @Resource
    ProductCategoryMapper productCategoryMapper;
    @Resource
    ProductMapper productMapper;

    private ProductCategoryEntity wrapper(ProductCategory productCategory) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity(this);
        BeanUtils.copyProperties(productCategory, productCategoryEntity);
        return productCategoryEntity;
    }

    public void save(ProductCategoryEntity productCategoryEntity) {
        if (productCategoryEntity.getId() == null) {
            productCategoryMapper.insert(productCategoryEntity);
        } else {
            productCategoryMapper.updateByPrimaryKey(productCategoryEntity);
        }
    }

    public int delete(Long id) {
        return productCategoryMapper.updateDeletedById(Boolean.TRUE, id);
    }

    /**
     *
     * @param id product category id
     * @return ProductCategoryEntity
     * @throws BussException
     */
    public ProductCategoryEntity byId(Long id) throws BussException {
        ProductCategory productCategory = productCategoryMapper.selectByPrimaryKey(id);
        if (productCategory == null) {
            throw new BussException(ProductError.CATEGORY_NOT_FOUND);
        }
        return wrapper(productCategory);
    }

    /**
     * 查询 商品数目
     *
     * @param id category id
     * @return product total num under category id
     */
    public Long productTotalNum(Long id) {
        return productMapper.countByCategoryId(id);
    }
}
