package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.form.order.*;
import cn.jimoos.form.order.be.BeOrderQueryForm;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.OrderVO;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-12 15:06.
 */
public interface OrderService {
    /**
     * 添加商城 订单
     *
     * @param orderForm   orderForm
     * @param userAddress userAddress
     * @return order vo
     * @throws BussException the buss exception
     */
    OrderVO addProductOrder(OrderForm orderForm, UserAddress userAddress) throws BussException;

    /**
     * 获取订单详情
     *
     * @param userId  user Id
     * @param orderId order Id
     * @return OrderVO
     * @throws BussException OrderError.ORDER_NOT_FOUND
     */
    OrderVO getOne(Long userId, Long orderId) throws BussException;

    /**
     * 查询用户的订单列表
     *
     * @param form 表单
     * @return List<OrderVO>
     */
    List<OrderVO> userOrders(UserOrderQueryForm form);

    /**
     * 发货
     *
     * @param orderDeliverForm OrderDeliverForm
     * @return OrderEntity object
     * @throws BussException the buss exception
     */
    OrderEntity deliver(OrderDeliverForm orderDeliverForm) throws BussException;

    /**
     * 用户确认收货
     *
     * @param confirmForm the confirm form
     * @return OrderEntity object
     * @throws BussException the buss exception
     */
    OrderEntity confirmOrder(ConfirmForm confirmForm) throws BussException;

    /**
     * 提醒发货
     *
     * @param remindDeliveryForm the remind delivery form
     * @throws BussException the buss exception
     */
    void remindDelivery(RemindDeliveryForm remindDeliveryForm) throws BussException;

    /**
     * 取消订单
     *
     * @param cancelForm the cancel form
     * @return order vo
     * @throws BussException the buss exception
     */
    OrderVO cancelOrder(CancelForm cancelForm) throws BussException;

    /**
     * 封装了 取消订单
     *
     * @param cancelForm the cancel form
     * @return order vo
     */
    OrderVO cancelOrderWrapperException(CancelForm cancelForm);

    /**
     * 查询订单列表
     *
     * @param beOrderQueryForm back-end order query form
     * @return Page<OrderVO>
     */
    Page<OrderVO> qTable(BeOrderQueryForm beOrderQueryForm);
}
