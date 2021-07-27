package cn.jimoos.entity;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.constant.OrderStatus;
import cn.jimoos.form.order.be.BeRefundQueryForm;
import cn.jimoos.model.Order;
import cn.jimoos.model.OrderRefund;
import cn.jimoos.repository.OrderRepository;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.OrderRefundVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SiletFlower
 * @date 2021/7/27 13:13:15
 * @description
 */
@Data
@NoArgsConstructor
public class OrderRefundEntity extends OrderRefund {
    private OrderRepository orderRepository;

    public OrderRefundEntity(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 将原订单数据迁移至退款订单表内
     */
    public void dataMigration(OrderEntity orderEntity) {
        this.setOrderAmount(orderEntity.getRealPay());
        this.setRefundAmount(orderEntity.getRealPay());
        this.setRefundStatus(OrderStatus.REFUND);
    }

    // dao层操作相关

    /**
     * 寻找原订单
     * @throws BussException
     * @return
     */
    public OrderEntity findOrder() throws BussException {
        return orderRepository.findByNum(this.getOrderNum());
    }

    /**
     * 新增或修改保存数据
     */
    public void save() {
        orderRepository.saveOrderRefund(this);
    }

    /**
     * 修改原订单的状态
     */
    public void updateOrderStatus(byte status) {
        Order order = new Order();
        order.setStatus(status);
        orderRepository.updateByOrderNum(order, this.getOrderNum());
    }

    /**
     * 后台分页查询 退款订单
     * @param beRefundQueryForm
     * @return
     */
    public Page<OrderRefundVO> findRefundByParamForPage(BeRefundQueryForm beRefundQueryForm) {
        return orderRepository.findRefundByParamForPage(beRefundQueryForm);
    }
}
