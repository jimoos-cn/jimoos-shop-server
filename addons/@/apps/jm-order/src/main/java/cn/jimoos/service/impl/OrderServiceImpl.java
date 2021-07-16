package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.context.DiscountContext;
import cn.jimoos.context.FeeContext;
import cn.jimoos.dao.*;
import cn.jimoos.dic.ShipmentType;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.ShopOrderEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.factory.OrderFactory;
import cn.jimoos.form.order.*;
import cn.jimoos.form.order.be.BeOrderQueryForm;
import cn.jimoos.model.*;
import cn.jimoos.repository.OrderRepository;
import cn.jimoos.repository.ShipmentRepository;
import cn.jimoos.service.OrderService;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.user.provider.UserProvider;
import cn.jimoos.user.vo.UserVO;
import cn.jimoos.utils.http.Page;
import cn.jimoos.utils.validate.ValidateUtils;
import cn.jimoos.vo.OrderVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The type Order service.
 *
 * @author :keepcleargas
 * @date :2021-04-08 14:22.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    /**
     * The Order factory.
     */
    @Resource
    OrderFactory orderFactory;
    /**
     * The Order repository.
     */
    @Resource
    OrderRepository orderRepository;

    @Resource
    ShipmentRepository shipmentRepository;

    @Resource
    UserProvider userProvider;

    /**
     * The Order remind delivery mapper.
     */
    @Resource
    OrderRemindDeliveryMapper orderRemindDeliveryMapper;
    /**
     * Order Dao Mapper For Read
     */
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;
    @Resource
    OrderItemDiscountMapper orderItemDiscountMapper;
    @Resource
    OrderItemFeeMapper orderItemFeeMapper;
    @Resource
    ShipmentMapper shipmentMapper;

    @Override
    public OrderVO addProductOrder(OrderForm orderForm, UserAddress userAddress) throws BussException {
        ValidateUtils.validate(orderForm);
        if (userAddress == null) {
            throw new BussException(OrderError.ADDRESS_NOT_FOUND);
        }
        ShopOrderEntity orderEntity = orderFactory.createShopOrderEntity(orderForm, userAddress);

        orderRepository.saveOrderEntity(orderEntity);
        //创建优惠
        DiscountContext discountContext = new DiscountContext(orderForm.getItemDiscounts());
        List<OrderItemDiscount> orderItemDiscounts = orderFactory.createOrderItemDiscounts(discountContext.getDiscountItems(), orderEntity.getOrderItems());
        //保存优惠
        orderRepository.saveOrderItemDiscount(orderItemDiscounts);
        //add to entity
        orderEntity.addDiscountItems(orderItemDiscounts);
        //创建费用
        FeeContext feeContext = new FeeContext(orderForm.getItemFees());
        List<OrderItemFee> orderItemFees = orderFactory.createOrderItemFees(feeContext.getFeeItems(), orderEntity.getOrderItems());
        //保存费用
        orderRepository.saveOrderItemFees(orderItemFees);
        //add to entity
        orderEntity.addFeeItems(orderItemFees);
        //获取订单配送信息
        orderEntity.getShipment();

        return OrderVO.fromEntity(orderEntity);
    }

    @Override
    public OrderVO getOne(Long userId, Long orderId) throws BussException {
        OrderEntity orderEntity = orderRepository.findById(orderId);

        if (!orderEntity.getUserId().equals(userId)) {
            //用户信息传参不正确 则 返回 null
            return null;
        }
        ShopOrderEntity shopOrderEntity = ShopOrderEntity.clone(orderEntity);

        return OrderVO.fromEntity(shopOrderEntity);
    }

    @Override
    public List<OrderVO> userOrders(UserOrderQueryForm form) {
        if (form.getUserId() <= 0) {
            return new ArrayList<>();
        }
        Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(4);
        queryMap.put("offset", form.getOffset());
        queryMap.put("limit", form.getLimit());
        queryMap.put("status", form.getStatus());
        queryMap.put("userId", form.getUserId());

        List<Order> orders = orderMapper.queryUserOrders(queryMap);

        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

       return fromOrders(orders);
    }

    @Override
    public OrderEntity deliver(OrderDeliverForm orderDeliverForm) throws BussException {
        ShopOrderEntity orderEntity = ShopOrderEntity.clone(orderRepository.findById(orderDeliverForm.getOrderId()));
        if (orderEntity != null) {
            orderEntity.deliver();
            orderRepository.saveStatus(orderEntity);
        }
        return orderEntity;
    }


    @Override
    public OrderEntity confirmOrder(ConfirmForm confirmForm) throws BussException {
        ShopOrderEntity orderEntity = ShopOrderEntity.clone(orderRepository.findByUidAndId(confirmForm.getUserId(), confirmForm.getOrderId()));

        if (!orderEntity.isDelivery()) {
            throw new BussException(OrderError.ORDER_STATUS_NOT_VALID);
        }
        orderEntity.confirm();

        orderRepository.saveStatus(orderEntity);
        return orderEntity;
    }


    @Override
    public void remindDelivery(RemindDeliveryForm remindDeliveryForm) {
        //todo 48小时外 + 48小时内 禁止重复提交 remind
        Long orderId = remindDeliveryForm.getOrderId();
        Long userId = remindDeliveryForm.getUserId();
        OrderRemindDelivery orderRemindDelivery = new OrderRemindDelivery();
        orderRemindDelivery.setOrderId(orderId);
        orderRemindDelivery.setUid(userId);
        long now = System.currentTimeMillis();
        orderRemindDelivery.setCreateAt(now);
        orderRemindDelivery.setUpdateAt(now);
        orderRemindDelivery.setDeleted(false);
        orderRemindDeliveryMapper.insert(orderRemindDelivery);
    }

    @Override
    public OrderVO cancelOrder(CancelForm cancelForm) throws BussException {
        if (cancelForm.getOrderId() == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        OrderEntity orderEntity = orderRepository.findByUidAndId(cancelForm.getUserId(), cancelForm.getOrderId());

        if (orderEntity == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }

        if (!orderEntity.isNew()) {
            throw new BussException(OrderError.ORDER_STATUS_NOT_VALID);
        }

        orderEntity.cancel();

        orderRepository.saveCancel(orderEntity);

        OrderVO orderVo = new OrderVO();
        BeanUtils.copyProperties(orderEntity, orderVo);
        return orderVo;
    }


    @Override
    public OrderVO cancelOrderWrapperException(CancelForm cancelForm) {
        try {
            if (cancelForm.getOrderId() == null) {
                throw new BussException(OrderError.ORDER_NOT_FOUND);
            }
            OrderEntity orderEntity = orderRepository.findById(cancelForm.getOrderId());

            if (orderEntity != null) {
                orderEntity.cancel();

                orderRepository.saveCancel(orderEntity);
                OrderVO orderVo = new OrderVO();
                BeanUtils.copyProperties(orderEntity, orderVo);
                return orderVo;
            }
        } catch (BussException e) {
            log.error("取消订单 发生异常{}", cancelForm.getOrderId());
        }
        return null;
    }


    @Override
    public Page<OrderVO> qTable(BeOrderQueryForm form) {
        Map<String, Object> queryMap = form.toQueryMap();

        if (!StringUtils.isEmpty(form.getPhone())) {
            UserVO userVO = userProvider.byPhone(form.getPhone());

            if (userVO == null) {
                return Page.empty();
            }
            queryMap.put("userId", userVO.getId());
        }
        long count = orderMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            List<Order> orders = orderMapper.queryTable(form.toQueryMap());

            return Page.create(count,fromOrders(orders));
        }
        return Page.empty();
    }

    @Override
    public Page<OrderVO> qTableByUid(BeOrderQueryForm beOrderQueryForm) {
        if (beOrderQueryForm.getUserId() == null) {
            return Page.empty();
        }
        long count = orderMapper.queryTableCount(beOrderQueryForm.toQueryMap());
        if (count > 0) {
            List<Order> orders = orderMapper.queryUserOrders(beOrderQueryForm.toQueryMap());
            return Page.create(count, fromOrders(orders));
        }
        return Page.empty();
    }

    @Override
    public OrderVO getOrderDetails(Long orderId) throws BussException {
        if (orderId != null) {
            OrderEntity orderEntity = orderRepository.findById(orderId);
            orderEntity.setOrderRepository(orderRepository);
            orderEntity.setShipmentRepository(shipmentRepository);
            return OrderVO.toVO(orderEntity);
        }
        return new OrderVO();
    }

    /**
     * 从 List<Order> => List<OrderVO>
     * @param orders 原订单列表
     * @return 返回订单详情
     */
    private List<OrderVO> fromOrders(List<Order> orders){
        List<Long> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
        List<String> orderNums = orders.stream().map(Order::getOrderNum).collect(Collectors.toList());

        List<OrderItem> orderItems = orderItemMapper.findByOrderIdIn(orderIds);
        List<OrderItemDiscount> orderItemDiscounts = orderItemDiscountMapper.findByOrderIdIn(orderIds);
        List<OrderItemFee> orderItemFees = orderItemFeeMapper.findByOrderIdIn(orderIds);
        List<Shipment> shipments = shipmentMapper.findByTypeAndOutTradeNoIn(ShipmentType.DEFAULT, orderNums);

        Map<Long, List<OrderItem>> id2ItemListMap =
                orderItems.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        Map<Long, List<OrderItemDiscount>> id2ItemDiscountListMap =
                orderItemDiscounts.stream().collect(Collectors.groupingBy(OrderItemDiscount::getOrderId));
        Map<Long, List<OrderItemFee>> id2ItemFeeListMap =
                orderItemFees.stream().collect(Collectors.groupingBy(OrderItemFee::getOrderId));
        Map<String, Shipment> outTradeNo2ShipmentMap = shipments.stream().collect(Collectors.toMap(Shipment::getOutTradeNo, Function.identity()));

        return orders.stream().map(order -> {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setOrderItems(id2ItemListMap.get(order.getId()));
            orderVO.setOrderDiscounts(id2ItemDiscountListMap.get(order.getId()));
            orderVO.setOrderItemFees(id2ItemFeeListMap.get(order.getId()));
            orderVO.setShipment(outTradeNo2ShipmentMap.get(order.getOrderNum()));
            return orderVO;
        }).collect(Collectors.toList());
    }
}
