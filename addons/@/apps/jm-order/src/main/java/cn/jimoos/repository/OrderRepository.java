package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.*;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.OrderRefundEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.form.order.be.BeRefundQueryForm;
import cn.jimoos.model.*;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.OrderRefundVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Order repository.
 *
 * @author :keepcleargas
 * @date :2021-04-07 12:59.
 */
@Repository
@Slf4j
public class OrderRepository {
    /**
     * The Order mapper.
     */
    @Resource
    OrderMapper orderMapper;
    /**
     * The Order item mapper.
     */
    @Resource
    OrderItemMapper orderItemMapper;
    /**
     * The Order item discount mapper.
     */
    @Resource
    OrderItemDiscountMapper orderItemDiscountMapper;
    /**
     * The Order item fee mapper.
     */
    @Resource
    OrderItemFeeMapper orderItemFeeMapper;
    /**
     * The shipment mapper.
     */
    @Resource
    ShipmentMapper shipmentMapper;
    /**
     * 订单退款mapper
     */
    @Resource
    OrderRefundMapper orderRefundMapper;

    /**
     * Save status.
     *
     * @param orderEntity the order entity
     */
    public void saveStatus(OrderEntity orderEntity) {
        orderMapper.updateByPrimaryKey(orderEntity);
    }

    /**
     * 保存订单取消状态
     *
     * @param orderEntity orderEntity
     */
    public void saveCancel(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderMapper.updateByPrimaryKey(orderEntity);

            List<OrderItemDiscount> orderItemDiscounts = orderEntity.getOrderItemDiscountInputs();
            if (!CollectionUtils.isEmpty(orderItemDiscounts)) {
                for (OrderItemDiscount orderItemDiscount : orderItemDiscounts) {
                    orderItemDiscountMapper.updateByPrimaryKey(orderItemDiscount);
                }
            }
        }
    }


    /**
     * 保存订单申请退款状态
     *
     * @param orderEntity orderEntity
     */
    public void saveRefund(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderMapper.updateByPrimaryKey(orderEntity);
        }
    }

    /**
     * 通用的 产品购买
     *
     * @param orderEntity orderEntity
     */
    public void saveOrderEntity(OrderEntity orderEntity) {
        orderMapper.insert(orderEntity);
        String orderNum = genOrderNum(orderEntity.getId(), 0);
        orderEntity.setOrderNum(orderNum);

        orderMapper.updateByPrimaryKey(orderEntity);

        //插入商品列表
        List<OrderItem> orderItems = orderEntity.getOrderItemInputs();

        orderItems = orderItems.stream().peek(orderItem -> orderItem.setOrderId(orderEntity.getId())).collect(Collectors.toList());

        orderItemMapper.batchInsert(orderItems);
    }

    /**
     * 插入优惠信息
     *
     * @param orderItemDiscounts the order item discounts
     */
    public void saveOrderItemDiscount(List<OrderItemDiscount> orderItemDiscounts) {
        if (!CollectionUtils.isEmpty(orderItemDiscounts)) {
            orderItemDiscountMapper.batchInsert(orderItemDiscounts);
        }
    }


    /**
     * Wrapper order entity.
     *
     * @param order the order
     * @return the order entity
     */
    public OrderEntity wrapper(Order order) {
        OrderEntity orderEntity = new OrderEntity(this);
        BeanUtils.copyProperties(order, orderEntity);
        return orderEntity;
    }

    /**
     * Gets item by order id.
     *
     * @param orderId the order id
     * @return the item by order id
     */
    public List<OrderItem> getItemByOrderId(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    /**
     * 根据 orderId 获取订单
     *
     * @param orderId orderId
     * @return OrderEntity order entity
     * @throws BussException the buss exception
     */
    public OrderEntity findById(Long orderId) throws BussException {
        Order order = orderMapper.selectByPrimaryKey(orderId);

        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        return wrapper(order);
    }

    /**
     * Find by uid and id order entity.
     *
     * @param uid     the uid
     * @param orderId the order id
     * @return the order entity
     * @throws BussException the buss exception
     */
    public OrderEntity findByUidAndId(Long uid, Long orderId) throws BussException {
        Order order = orderMapper.findOneByUserIdAndId(uid, orderId);
        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        return wrapper(order);
    }

    /**
     * 根据订单编号 查询订单
     *
     * @param orderNum the order num
     * @return order entity
     * @throws BussException the buss exception
     */
    public OrderEntity findByNum(String orderNum) throws BussException {
        Order order = orderMapper.findOneByOrderNum(orderNum);

        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        return wrapper(order);
    }


    /**
     * 获取 订单里的 按商品ID 归类的优惠记录
     *
     * @param id the id
     * @return order item discounts by order id
     */
    public List<OrderItemDiscount> getOrderItemDiscountsByOrderId(Long id) {
        return orderItemDiscountMapper.findByOrderId(id);
    }

    /**
     * Save order item fees.
     *
     * @param orderItemFees the order item fees
     */
    public void saveOrderItemFees(List<OrderItemFee> orderItemFees) {
        if (!CollectionUtils.isEmpty(orderItemFees)) {
            orderItemFeeMapper.batchInsert(orderItemFees);
        }
    }

    /**
     * 根据订单 id 获取 该订单下的费用
     *
     * @param orderId order id
     * @return List<OrderItemFee>
     */
    public List<OrderItemFee> getOrderItemFeeByOrderId(Long orderId) {
        return orderItemFeeMapper.findByOrderId(orderId);
    }

    /**
     * 获取 根据订单 获取配送信息
     *
     * @param type       配送类别
     * @param outTradeNo 外部交易编号
     * @return Shipment
     */
    public Shipment getShipment(int type, String outTradeNo) {
        return shipmentMapper.findOneByTypeAndOutTradeNo(type, outTradeNo);
    }

    private String genOrderNum(long orderId, int channel) {
        Random r = new Random();
        //产生2个0-9的随机数
        int r1 = r.nextInt(9);
        int r2 = r.nextInt(9);
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String sequ = new DecimalFormat("0000000").format(orderId);
        return date + channel + r1 + r2 + sequ;
    }

    /**
     * 保存退款订单申请
     * @param orderRefundEntity
     */
    public boolean saveOrderRefund(OrderRefundEntity orderRefundEntity) {
        int row = 0;
        if (orderRefundEntity.getId() == null) {
            row = orderRefundMapper.insert(orderRefundEntity);
        }else{
            row = orderRefundMapper.updateByPrimaryKeySelective(orderRefundEntity);
        }
        Assert.isTrue(row > 0,"orderRefund 保存数据失败");
        return true;
    }

    /**
     * 根据orderNum更新订单
     * @param order
     * @param orderNum
     * @return
     */
    public boolean updateByOrderNum(Order order, String orderNum) {
        int i = orderMapper.updateByOrderNum(order, orderNum);
        Assert.isTrue(i > 0, "updateByOrderNum 更新订单失败");
        return true;
    }

    public Page<OrderRefundVO> findRefundByParamForPage(BeRefundQueryForm beRefundQueryForm) {
        List<OrderRefund> list = orderRefundMapper.findRefundByParamForPage(beRefundQueryForm);
        List<OrderRefundVO> collect = list.stream().map(item -> {
            OrderRefundVO orderRefundVO = new OrderRefundVO();
            BeanUtils.copyProperties(item, orderRefundVO);
            // 根据orderNum查询订单商品
            List<OrderItem> items = orderItemMapper.findByOrderNum(item.getOrderNum());
            orderRefundVO.setOrderItems(items);
            return orderRefundVO;
        }).collect(Collectors.toList());
        return new Page<>((long)collect.size(),collect);
    }
}
