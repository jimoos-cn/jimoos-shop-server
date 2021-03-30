package cn.jimoos.service.impl;

import cn.jimoos.dao.ProductAttrMapper;
import cn.jimoos.form.attr.*;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import cn.jimoos.repository.ProductAttrRepository;
import cn.jimoos.service.ProductAttrService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductAttrVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:37.
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {
    @Resource
    ProductAttrMapper productAttrMapper;
    @Resource
    ProductAttrRepository productAttrRepository;

    @Override
    public ProductAttrVO addAttr(BeProductAttrForm productAttrForm) {
        return null;
    }

    @Override
    public ProductAttrVO updateAttr(BeProductAttrForm productAttrForm) {
        return null;
    }

    @Override
    public int delete(BeProductAttrDeleteForm form) {
        return 0;
    }

    @Override
    public Page<ProductAttr> query(BeProductAttrQueryForm form) {
        long count = productAttrMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            return Page.create(count, productAttrMapper.queryTable(form.toQueryMap()));
        }
        return Page.empty();
    }

    @Override
    public List<ProductAttrValue> attrValues(Long attrId) {
        return null;
    }

    @Override
    public ProductAttrValue updateAttrValue(BeAttrValueForm form) {
        return null;
    }

    @Override
    public List<ProductAttrValue> saveAttrValues(BeAttrValuesForm attrValuesForm) {
        return null;
    }

    @Override
    public int deleteAttrValue(BeAttrValueDeleteForm attrValueDeleteForm) {
        return 0;
    }
}
