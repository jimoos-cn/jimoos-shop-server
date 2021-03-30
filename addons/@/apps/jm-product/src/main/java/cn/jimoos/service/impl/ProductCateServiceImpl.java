package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductCategoryMapper;
import cn.jimoos.entity.ProductCategoryEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.factory.ProductCateFactory;
import cn.jimoos.form.category.BeProductCateCreateForm;
import cn.jimoos.form.category.BeProductCateDeleteForm;
import cn.jimoos.form.category.BeProductCateSearchForm;
import cn.jimoos.form.category.BeProductCateUpdateForm;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.repository.ProductCategoryRepository;
import cn.jimoos.service.ProductCateService;
import cn.jimoos.utils.http.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:17.
 */
@Service
public class ProductCateServiceImpl implements ProductCateService {
    @Resource
    ProductCateFactory productCateFactory;
    @Resource
    ProductCategoryRepository productCategoryRepository;
    @Resource
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory create(BeProductCateCreateForm form) {
        ProductCategoryEntity productCategoryEntity = productCateFactory.create(form);

        productCategoryRepository.save(productCategoryEntity);
        return productCategoryEntity;
    }

    @Override
    public ProductCategory update(BeProductCateUpdateForm form) throws BussException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.byId(form.getId());

        productCategoryEntity.update(form);
        productCategoryRepository.save(productCategoryEntity);
        return productCategoryEntity;
    }

    @Override
    public Page<ProductCategory> query(BeProductCateSearchForm form) {
        long count = productCategoryMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            return Page.create(count, productCategoryMapper.queryTable(form.toQueryMap()));
        }
        return Page.empty();
    }

    @Override
    public List<ProductCategory> children(Long pid) {
        return productCategoryMapper.findByPid(pid);
    }

    @Override
    public int delete(BeProductCateDeleteForm form) throws BussException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.byId(form.getCategoryId());
        //被绑定了不能删除
        if (productCategoryEntity.ifBind()) {
            throw new BussException(ProductError.CATEGORY_USED);
        }
        return productCategoryRepository.delete(form.getCategoryId());
    }
}
