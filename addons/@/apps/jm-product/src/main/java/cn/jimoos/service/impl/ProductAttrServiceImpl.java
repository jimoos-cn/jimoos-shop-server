package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.ProductAttrMapper;
import cn.jimoos.dao.ProductAttrValueMapper;
import cn.jimoos.entity.ProductAttrEntity;
import cn.jimoos.error.ProductError;
import cn.jimoos.factory.ProductAttrFactory;
import cn.jimoos.form.attr.*;
import cn.jimoos.model.ProductAttr;
import cn.jimoos.model.ProductAttrValue;
import cn.jimoos.repository.ProductAttrRepository;
import cn.jimoos.service.ProductAttrService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductAttrVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:37.
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {
    @Resource
    ProductAttrFactory productAttrFactory;
    @Resource
    ProductAttrMapper productAttrMapper;
    @Resource
    ProductAttrRepository productAttrRepository;
    @Resource
    ProductAttrValueMapper productAttrValueMapper;

    @Override
    public ProductAttrVO addAttr(BeProductAttrForm productAttrForm) {
        ProductAttrEntity productAttrEntity = productAttrFactory.create(productAttrForm);

        productAttrRepository.save(productAttrEntity);
        ProductAttrVO productAttrVO = new ProductAttrVO();
        BeanUtils.copyProperties(productAttrEntity, productAttrVO);
        return productAttrVO;
    }

    @Override
    public ProductAttrVO getOne(Long id) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(id);

        ProductAttrVO productAttrVO = new ProductAttrVO();
        BeanUtils.copyProperties(productAttrEntity, productAttrVO);
        return productAttrVO;
    }

    @Override
    public ProductAttrVO updateAttr(BeProductAttrForm productAttrForm) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(productAttrForm.getId());

        productAttrEntity.update(productAttrForm);
        productAttrRepository.save(productAttrEntity);

        ProductAttrVO productAttrVO = new ProductAttrVO();
        BeanUtils.copyProperties(productAttrEntity, productAttrVO);
        return productAttrVO;
    }

    @Override
    public int delete(BeProductAttrDeleteForm form) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(form.getAttrId());

        if (productAttrEntity.ifBind()) {
            throw new BussException(ProductError.ATTR_USED);
        }

        return productAttrRepository.delete(form.getAttrId());
    }

    @Override
    public Page<ProductAttrVO> query(BeProductAttrQueryForm form) {
        long count = productAttrMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            return Page.create(count, fromAttrList(productAttrMapper.queryTable(form.toQueryMap())));
        }
        return Page.empty();
    }


    @Override
    public List<ProductAttrValue> attrValues(Long attrId) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(attrId);
        return productAttrEntity.getAttrValues();
    }

    @Override
    public ProductAttrValue updateAttrValue(BeAttrValueForm form) throws BussException {
        ProductAttrValue productAttrValue = productAttrRepository.findAttrValueByValueId(form.getId());

        if (productAttrValue == null) {
            throw new BussException(ProductError.ATTR_VALUE_NOT_FOUND);
        }
        BeanUtils.copyProperties(form, productAttrValue);
        productAttrValue.setUpdateAt(System.currentTimeMillis());
        productAttrRepository.updateAttrValue(productAttrValue);
        return productAttrValue;
    }

    @Override
    public List<ProductAttrValue> saveAttrValues(BeAttrValuesForm attrValuesForm) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(attrValuesForm.getAttrId());

        productAttrEntity.addAttrValues(attrValuesForm.getValueForms());
        productAttrRepository.saveAttrValues(productAttrEntity);
        return productAttrEntity.getAttrValues();
    }

    @Override
    public int deleteAttrValue(BeAttrValueDeleteForm attrValueDeleteForm) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(attrValueDeleteForm.getAttrId());

        if (productAttrEntity.ifBindValue(attrValueDeleteForm.getAttrValueId())) {
            throw new BussException(ProductError.ATTR_VALUE_USED);
        }
        return productAttrRepository.deleteAttrValue(attrValueDeleteForm.getAttrValueId());
    }

    @Override
    public ProductAttrValue addAttrValue(BeAttrValueForm beAttrValueForm) throws BussException {
        ProductAttrEntity productAttrEntity = productAttrRepository.byIdThrow(beAttrValueForm.getAttrId());
        //增量添加一个
        productAttrEntity.addAttrValue(beAttrValueForm);

        return productAttrRepository.saveOneAttrValue(productAttrEntity);
    }

    private List<ProductAttrVO> fromAttrList(List<ProductAttr> productAttrs) {
        List<Long> attrIds = productAttrs.stream().map(ProductAttr::getId).collect(Collectors.toList());

        List<ProductAttrValue> productAttrValues = productAttrValueMapper.findByAttrIdIn(attrIds);
        Map<Long, List<ProductAttrValue>> id2ListMap = productAttrValues.stream().collect(Collectors.groupingBy(ProductAttrValue::getAttrId));

        return productAttrs.stream().map(productAttr -> {
            ProductAttrVO productAttrVO = new ProductAttrVO();
            BeanUtils.copyProperties(productAttr, productAttrVO);
            productAttrVO.setAttrValues(id2ListMap.get(productAttr.getId()));
            return productAttrVO;
        }).collect(Collectors.toList());
    }
}
