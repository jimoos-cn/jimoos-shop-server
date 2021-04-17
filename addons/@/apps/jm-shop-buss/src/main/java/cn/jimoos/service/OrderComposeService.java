package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.order.CancelForm;
import cn.jimoos.form.order.ConfirmForm;
import cn.jimoos.form.order.OrderForm;
import cn.jimoos.vo.OrderVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 复合订单处理
 *
 * @author :keepcleargas
 * @date :2021-04-13 16:48.
 */
public interface OrderComposeService {
    /**
     * 实体商品订单
     *
     * @param orderForm 订单表单
     * @return OrderVO
     * @throws BussException OrderError.ADDRESS_NOT_FOUND
     */
    OrderVO addProductOrder(@RequestBody OrderForm orderForm) throws BussException;

    /**
     * 确认订单
     *
     * @param confirmForm 确认表单
     * @throws BussException OrderError.ORDER_STATUS_NOT_VALID
     */
    void deliverProductOrder(ConfirmForm confirmForm) throws BussException;

    /**
     * 取消订单
     *
     * @param cancelForm 取消表单
     * @throws BussException OrderError
     */
    void cancelOrder(CancelForm cancelForm) throws BussException;

    /**
     * 取消订单 不抛出异常
     *
     * @param cancelForm 取消表单
     * @throws BussException OrderError
     */
    void cancelOrderExceptionWrapper(CancelForm cancelForm);
}
