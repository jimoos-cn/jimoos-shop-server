package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.ProductEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.error.ProductError;
import cn.jimoos.form.order.CartForm;
import cn.jimoos.form.order.CartItemForm;
import cn.jimoos.form.order.OrderItemForm;
import cn.jimoos.form.product.ProductSearchForm;
import cn.jimoos.model.UserProductCollection;
import cn.jimoos.product.model.ProductItem;
import cn.jimoos.product.provider.IProductProvider;
import cn.jimoos.service.ProductCollectService;
import cn.jimoos.service.ProductService;
import cn.jimoos.vo.ProductVO;
import cn.jimoos.vo.order.OrderPreVO;
import org.springframework.util.CollectionUtils;
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
    @Resource
    IProductProvider productProvider;

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

        if (productVO.getStatus() != ProductEntity.Status.LISTED.val()) {

        }
        if (userId > 0) {
            productVO.setCollect(productCollectService.findByUserIdAndProductId(userId, productId) != null);
        }
        return productVO;
    }

    /**
     * 立即下单
     *
     * @param userId    user Id
     * @param productId product Id
     * @param skuId     sku Id
     * @param num       购买数量
     * @return OrderPreVO 生成预览订单
     */
    @GetMapping(value = "/{productId}/skus/{skuId}/buy", produces = "application/json; charset=utf-8")
    public OrderPreVO buy(@RequestParam("userId") Long userId,
                          @PathVariable("productId") Long productId,
                          @PathVariable("skuId") Long skuId,
                          @RequestParam("num") Integer num) throws BussException {
        ProductItem productItem = productProvider.byId(skuId);

        if (productItem == null) {
            throw new BussException(ProductError.PRODUCT_NOT_EXIST);
        }

        OrderPreVO result = new OrderPreVO();
        result.setUserId(userId);
        result.addOrderItem(new OrderItemForm(productItem, num));

        //todo 优惠券可用 返回

        return result;
    }

    /**
     * 购物车下单
     *
     * @param cartForm 购物车表单
     * @return OrderPreVO
     * @throws BussException OrderError.CART_EMPTY
     */
    @PostMapping(value = "/cart/buy", produces = "application/json; charset=utf-8")
    public OrderPreVO buy(@RequestBody CartForm cartForm) throws BussException {
        if (CollectionUtils.isEmpty(cartForm.getCartItems())) {
            throw new BussException(OrderError.CART_EMPTY);
        }

        OrderPreVO result = new OrderPreVO();

        result.setUserId(cartForm.getUserId());


        List<ProductItem> productItems = productProvider.byIds(cartForm.getCartItems().stream().map(CartItemForm::getSkuId).collect(Collectors.toList()));
        Map<Long, Integer> idToNumberFormMap = cartForm.getCartItems().stream().collect(Collectors.toMap(CartItemForm::getSkuId, CartItemForm::getNumber));

        if (CollectionUtils.isEmpty(productItems)) {
            throw new BussException(ProductError.PRODUCT_NOT_EXIST);
        }

        for (ProductItem productItem : productItems) {
            result.addOrderItem(new OrderItemForm(productItem, idToNumberFormMap.get(productItem.getProductId())));
        }
        //todo 优惠券可用 返回

        return result;
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
