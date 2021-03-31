package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.ProductEntity;
import cn.jimoos.factory.ProductFactory;
import cn.jimoos.form.product.*;
import cn.jimoos.repository.ProductRepository;
import cn.jimoos.service.ProductService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductSkuVO;
import cn.jimoos.vo.ProductVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-31 11:32.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductFactory productFactory;
    @Resource
    ProductRepository productRepository;

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVO addFullProduct(BeProductForm beProductForm) {
        ProductEntity productEntity = productFactory.create(beProductForm);
        //绑定tags
        productEntity.attachTags(beProductForm.getTagIds());
        //新建信息
        productRepository.save(productEntity);
        //更新sku数据
        List<BeProductForm.SkuInput> skuInputs = beProductForm.getSkus();

        if (!CollectionUtils.isEmpty(skuInputs)) {
            //添加 sku
            productEntity.addSkus(skuInputs);
            productRepository.saveSkus(productEntity);
        }
        return productEntity.toVO();
    }

    @Override
    public ProductVO saveProductInfo(BeProductForm beProductForm) {
        ProductEntity productEntity = productFactory.create(beProductForm);
        //绑定tags
        productEntity.attachTags(beProductForm.getTagIds());
        //新建信息
        productRepository.save(productEntity);
        return productEntity.toVO();
    }

    @Override
    public List<ProductSkuVO> saveSkus(BeProductSkusForm beProductSkusForm) throws BussException {
        ProductEntity productEntity = productRepository.byId(beProductSkusForm.getProductId());

        if (!CollectionUtils.isEmpty(beProductSkusForm.getSkuInputs())) {
            if (beProductSkusForm.isBatchUpdate()) {
                productEntity.addSkus(beProductSkusForm.getSkuInputs());
                productRepository.saveSkus(productEntity);
            } else {
                BeProductForm.SkuInput skuInput = beProductSkusForm.getSkuInputs().get(0);
                ProductEntity.SkuEntity skuEntity = new ProductEntity.SkuEntity(productEntity, skuInput);
                skuEntity.addAttrMaps(skuInput.getAttrs());
                productRepository.updateOneSku(skuEntity);
                List<ProductSkuVO> productSkuVOs = new ArrayList<>();
                productSkuVOs.add(skuEntity.toVO());
                return productSkuVOs;
            }
        }
        return productEntity.getProductSkuVos();
    }

    @Override
    public ProductVO getOne(Long productId) {
        return null;
    }

    @Override
    public Page<ProductVO> query(BeProductQueryForm queryForm) {
        return null;
    }

    @Override
    public List<ProductSkuVO> skus(Long productId) {
        return new ArrayList<>();
    }

    @Override
    public int updateProductStatus(BeProductStatusForm statusForm) {
        return 0;
    }

    @Override
    public int delete(BeProductDeleteForm beProductDeleteForm) {
        return 0;
    }
}
