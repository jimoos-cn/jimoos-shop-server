package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductTagMapper;
import cn.jimoos.entity.ProductTagEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.factory.ProductTagFactory;
import cn.jimoos.form.tag.BeProductTagCreateForm;
import cn.jimoos.form.tag.BeProductTagDeleteForm;
import cn.jimoos.form.tag.BeProductTagSearchForm;
import cn.jimoos.form.tag.BeProductTagUpdateForm;
import cn.jimoos.model.ProductTag;
import cn.jimoos.repository.ProductTagRepository;
import cn.jimoos.service.ProductTagService;
import cn.jimoos.utils.http.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-29 21:03.
 */
@Service
public class ProductTagImpl implements ProductTagService {
    @Resource
    ProductTagFactory productTagFactory;
    @Resource
    ProductTagRepository productTagRepository;
    @Resource
    ProductTagMapper productTagMapper;

    @Override
    public ProductTag create(BeProductTagCreateForm form) {
        ProductTagEntity productTagEntity = productTagFactory.create(form);

        productTagRepository.save(productTagEntity);
        return productTagEntity;
    }

    @Override
    public ProductTag update(BeProductTagUpdateForm form) throws BussException {
        ProductTagEntity productTagEntity = productTagRepository.byId(form.getId());

        productTagEntity.update(form);
        productTagRepository.save(productTagEntity);
        return productTagEntity;
    }

    @Override
    public Page<ProductTag> query(BeProductTagSearchForm form) {
        Long count = productTagMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            return Page.create(count, productTagMapper.queryTable(form.toQueryMap()));
        }
        return Page.empty();
    }

    @Override
    public int delete(BeProductTagDeleteForm form) throws BussException {
        ProductTagEntity productTagEntity = productTagRepository.byId(form.getTagId());

        //被绑定了不能删除
        if (productTagEntity.ifBind()) {
            throw new BussException(ProductError.TAG_USED);
        }
        return productTagRepository.delete(form.getTagId());
    }
}
