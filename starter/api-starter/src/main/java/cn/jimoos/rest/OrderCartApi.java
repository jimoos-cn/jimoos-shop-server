package cn.jimoos.rest;

import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.form.cart.OrderCartIdsForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.product.model.ProductItem;
import cn.jimoos.product.provider.IProductProvider;
import cn.jimoos.service.OrderCartService;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import cn.jimoos.vo.order.OrderCartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 购物车接口
 *
 * @author :keepcleargas
 * @date :2021-04-12 14:34.
 */
@RestController
@RequestMapping("/v1/orderCarts")
public class OrderCartApi {
    @Resource
    OrderCartService orderCartService;
    @Resource
    IProductProvider productProvider;

    /**
     * 添加到购物车
     *
     * @param orderCartForm order cart form
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public OrderCart saveToCart(@ModelAttribute OrderCartForm orderCartForm) {
        return orderCartService.saveToCart(orderCartForm);
    }

    /**
     * 移除购物车
     *
     * @param orderCartIdsForm order cartId list
     */
    @PostMapping(value = "/remove", produces = "application/json; charset=utf-8")
    public void removeFromCart(@RequestBody OrderCartIdsForm orderCartIdsForm) {
        orderCartService.remove(orderCartIdsForm);
    }

    /**
     * 标记未 选中
     *
     * @param orderCartIdsForm order cartId list
     */
    @PostMapping(value = "/checked", produces = "application/json; charset=utf-8")
    public void checked(@RequestBody OrderCartIdsForm orderCartIdsForm) {
        orderCartService.checked(orderCartIdsForm);
    }

    /**
     * 标记为 未选中
     *
     * @param orderCartIdsForm order cartId list
     */
    @PostMapping(value = "/uncheck", produces = "application/json; charset=utf-8")
    public void uncheck(@RequestBody OrderCartIdsForm orderCartIdsForm) {
        orderCartService.uncheck(orderCartIdsForm);
    }

    /**
     * 获取购物车列表
     *
     * @param userForm4L user form
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<OrderCartVO> getUserCartItems(@ModelAttribute AbstractUserPageForm4L userForm4L) {
        List<OrderCart> orderCarts = orderCartService.getUserOrderCarts(userForm4L);

        if (CollectionUtils.isEmpty(orderCarts)) {
            return new ArrayList<>();
        } else {
            List<Long> skuIds = orderCarts.stream().map(OrderCart::getSkuId).collect(Collectors.toList());
            List<ProductItem> productItems = productProvider.byIds(skuIds);
            Map<Long, ProductItem> idToProductItemMap = productItems.stream().collect(Collectors.toMap(ProductItem::getSkuId, Function.identity()));

            return orderCarts.stream().map(orderCart -> {
                OrderCartVO orderCartVO = new OrderCartVO();
                BeanUtils.copyProperties(orderCart, orderCartVO);
                orderCartVO.setProductItem(idToProductItemMap.get(orderCart.getSkuId()));
                return orderCartVO;
            }).collect(Collectors.toList());
        }
    }
}
