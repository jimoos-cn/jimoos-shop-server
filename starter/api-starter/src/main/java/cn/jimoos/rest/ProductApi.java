package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.ProductEntity;
import cn.jimoos.form.product.ProductSearchForm;
import cn.jimoos.model.UserProductCollection;
import cn.jimoos.service.ProductCollectService;
import cn.jimoos.service.ProductService;
import cn.jimoos.vo.ProductVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品接口
 *
 * @author :keepcleargas
 * @date :2021-04-02 11:18.
 */
@RestController
@RequestMapping("/v1/products")
public class ProductApi {
    @Resource
    ProductService productService;
    @Resource
    ProductCollectService productCollectService;

    /**
     * 获取商品详情信息
     *
     * @param userId    user Id
     * @param productId product Id
     * @return ProductVO
     */
    @GetMapping(value = "/{productId}", produces = "application/json; charset=utf-8")
    public ProductVO getProduct(@RequestParam("userId") Long userId,
                                @PathVariable("productId") Long productId) throws BussException {
        ProductVO productVO = productService.getOne(productId);

        if(productVO.getStatus() != ProductEntity.Status.LISTED.val()){

        }
        if (userId > 0) {
            productVO.setCollect(productCollectService.findByUserIdAndProductId(userId, productId) != null);
        }
        return productVO;
    }

    /**
     * 查询商品
     *
     * @param form product search form
     * @return List<ProductVO>
     */
    @GetMapping(value = "/search", produces = "application/json; charset=utf-8")
    public List<ProductVO> getProduct(@ModelAttribute ProductSearchForm form) throws BussException {
        List<ProductVO> productVOS = productService.search(form);

        if (form.getUserId() > 0) {
            List<Long> productIds = productVOS.stream().map(ProductVO::getId).collect(Collectors.toList());
            List<UserProductCollection> userProductCollections = productCollectService.findByUserIdAndProductIdIn(form.getUserId(), productIds);
            Map<Long, UserProductCollection> idToUserProductCollectionMap =
                    userProductCollections.stream().collect(Collectors.toMap(UserProductCollection::getProductId, Function.identity()));

            return productVOS.stream()
                    .peek(productVO -> productVO.setCollect(idToUserProductCollectionMap.get(productVO.getId()) != null))
                    .collect(Collectors.toList());
        }
        return productVOS;
    }
}
