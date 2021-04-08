package cn.jimoos.service;

import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.form.cart.OrderCartIdsForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.utils.form.AbstractUserPageForm4L;

import java.util.List;

/**
 * 购物车服务
 *
 * @author :keepcleargas
 * @date :2021-04-07 12:55.
 */
public interface OrderCartService {
    /**
     * 添加购物车
     *
     * @param orderCartForm 购物车表单
     * @return OrderCart
     */
    OrderCart saveToCart(OrderCartForm orderCartForm);

    /**
     * 移除购物车
     *
     * @param orderCartIdForm 购物车 ItemId列表
     */
    void remove(OrderCartIdsForm orderCartIdForm);

    /**
     * 选中标识
     *
     * @param orderCartIdForm 购物车 ItemId列表
     */
    void checked(OrderCartIdsForm orderCartIdForm);

    /**
     * 取消标识
     *
     * @param orderCartIdForm 购物车 ItemId列表
     */
    void uncheck(OrderCartIdsForm orderCartIdForm);

    /**
     * 获取用户购物车
     *
     * @param form4L 用户表单
     * @return List<OrderCart>
     */
    List<OrderCart> getUserOrderCarts(AbstractUserPageForm4L form4L);
}
