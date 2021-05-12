package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.ProductEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.error.ProductError;
import cn.jimoos.factory.DiscountFactory;
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
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Resource
    DiscountFactory discountFactory;


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
            throw new BussException(ProductError.PRODUCT_NOT_LIST);
        }
        if (userId > 0) {
            productVO.setCollect(productCollectService.findByUserIdAndProductId(userId, productId) != null);
        }
        return productVO;
    }

    /**
     * 立即下单预览
     *
     * @param userId    user Id
     * @param productId product Id
     * @param skuId     sku Id
     * @param num       购买数量
     * @return OrderPreVO 生成预览订单
     */
    @GetMapping(value = "/{productId}/skus/{skuId}/buy", produces = "application/json; charset=utf-8")
    public OrderPreVO<String> buy(@RequestParam("userId") Long userId,
                                  @PathVariable("productId") Long productId,
                                  @PathVariable("skuId") Long skuId,
                                  @RequestParam("num") Integer num) throws BussException {
        ProductItem productItem = productProvider.byId(skuId);

        if (productItem == null) {
            throw new BussException(ProductError.PRODUCT_NOT_EXIST);
        }

        OrderPreVO<String> result = new OrderPreVO(null);
        result.setUserId(userId);
        result.addOrderItem(new OrderItemForm(productItem, num));

        List<BigDecimal> productPays = new ArrayList<>();
        productPays.add(result.getRealPay());

        List<ProductItem> productItems = new ArrayList<>();
        productItems.add(productItem);
        result.addDiscountItems(discountFactory.findBestCouponRecord(userId, result.getRealPay(), productItems, productPays));
        return result;
    }

    /**
     * 购物车下单预览
     *
     * @param cartForm 购物车表单
     * @return OrderPreVO
     * @throws BussException OrderError.CART_EMPTY
     */
    @PostMapping(value = "/cart/buy", produces = "application/json; charset=utf-8")
    public OrderPreVO<String> buy(@RequestBody CartForm cartForm) throws BussException {
        OrderPreVO<String> result = prepareOrderPreVO(cartForm);

        //准备订单商品对象
        List<ProductItem> productItems = prepareOrderItems(result, cartForm);
        //拆分价格
        List<BigDecimal> productPays = getProductPays(result, productItems);
        result.addDiscountItems(discountFactory.findBestCouponRecord(cartForm.getUserId(), result.getRealPay(), productItems, productPays));
        return result;
    }

    /**
     * 刷新下单预览
     *
     * @param cartForm 购物车表单
     * @return OrderPreVO
     * @throws BussException OrderError.CART_EMPTY
     */
    @PostMapping(value = "/refreshBuy", produces = "application/json; charset=utf-8")
    public OrderPreVO<String> refreshBuy(@RequestBody CartForm cartForm) throws BussException {
        OrderPreVO<String> result = prepareOrderPreVO(cartForm);
        List<ProductItem> productItems = prepareOrderItems(result, cartForm);

        List<BigDecimal> productPays = getProductPays(result, productItems);
        result.addDiscountItems(discountFactory.findByCouponRecordId(cartForm.getDiscountRecordId(), cartForm.getUserId(), result.getRealPay(), productItems, productPays));
        return result;
    }

    private OrderPreVO<String> prepareOrderPreVO(CartForm cartForm) throws BussException {
        if (CollectionUtils.isEmpty(cartForm.getCartItems())) {
            throw new BussException(OrderError.CART_EMPTY);
        }

        OrderPreVO<String> result = new OrderPreVO(null);

        result.setUserId(cartForm.getUserId());
        return result;
    }

    private List<BigDecimal> getProductPays(OrderPreVO<String> result, List<ProductItem> productItems) {
        List<BigDecimal> productPays = new ArrayList<>();
        //优惠券
        for (ProductItem productItem : productItems) {
            productPays.add(result.computeRealPayBySkuId(productItem.getSkuId()));
        }
        return productPays;
    }

    private List<ProductItem> prepareOrderItems(OrderPreVO<String> result, CartForm cartForm) throws BussException {
        List<ProductItem> productItems = productProvider.byIds(cartForm.getCartItems().stream().map(CartItemForm::getSkuId).collect(Collectors.toList()));
        Map<Long, Integer> idToNumberFormMap = cartForm.getCartItems().stream().collect(Collectors.toMap(CartItemForm::getSkuId, CartItemForm::getNumber));

        if (CollectionUtils.isEmpty(productItems)) {
            throw new BussException(ProductError.PRODUCT_NOT_EXIST);
        }

        for (ProductItem productItem : productItems) {
            result.addOrderItem(new OrderItemForm(productItem, idToNumberFormMap.get(productItem.getSkuId())));
        }
        return productItems;
    }

    /**
     * 查询商品
     *
     * @param form product search form
     * @return List<ProductVO>
     */
    @GetMapping(value = "/search", produces = "application/json; charset=utf-8")
    public List<ProductVO> getProduct(@ModelAttribute ProductSearchForm form) {
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


    /**
     * 收藏
     *
     * @param userId    user Id
     * @param productId product Id
     */
    @PostMapping(value = "/{productId}/collect", produces = "application/json; charset=utf-8")
    public void collect(@RequestParam("userId") Long userId,
                        @PathVariable("productId") Long productId) {
        productCollectService.collect(userId, productId);
    }

    /**
     * 取消商品收藏
     *
     * @param userId    user Id
     * @param productId product Id
     */
    @PostMapping(value = "/{productId}/unCollect", produces = "application/json; charset=utf-8")
    public void unCollect(@RequestParam("userId") Long userId,
                          @PathVariable("productId") Long productId) {
        productCollectService.unCollect(userId, productId);
    }
}
