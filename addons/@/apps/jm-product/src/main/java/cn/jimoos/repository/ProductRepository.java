package cn.jimoos.repository;

import cn.jimoos.dao.ProductMapper;
import cn.jimoos.dao.ProductSkuAttrMapMapper;
import cn.jimoos.dao.ProductSkuMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:44.
 */
@Repository
public class ProductRepository {
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    ProductSkuAttrMapMapper productSkuAttrMapMapper;

    
}
