package cn.jimoos.factory;

import cn.jimoos.context.DiscountContext;
import cn.jimoos.context.FeeContext;
import cn.jimoos.dic.OrderStatus;
import cn.jimoos.dic.ShipmentType;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.ShopOrderEntity;
import cn.jimoos.form.order.OrderForm;
import cn.jimoos.form.order.OrderItemForm;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.model.OrderItem;
import cn.jimoos.model.OrderItemDiscount;
import cn.jimoos.model.OrderItemFee;
import cn.jimoos.order.dic.DiscountType;
import cn.jimoos.order.dic.FeeType;
import cn.jimoos.repository.OrderRepository;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.utils.mapper.JsonMapper;
import cn.jimoos.utils.validate.ValidateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单存储
 *
 * @author :keepcleargas
 * @date :2021-04-08 14:18.
 */
@Component
public class OrderFactory {
    /**
     * The Order repository.
     */
    @Resource
    OrderRepository orderRepository;

    /**
     * Create order entity order entity.
     *
     * @param orderForm the order form
     * @return the order entity
     */
    public OrderEntity createOrderEntity(OrderForm orderForm) {
        ValidateUtils.validate(orderForm);
        OrderEntity orderEntity = new OrderEntity(orderRepository);
        //uid
        orderEntity.setUserId(orderForm.getUserId());
        //订单类型
        orderEntity.setOrderType(orderForm.getType());
        //子订单数目， 商品 `类型` 的数量，非件数
        orderEntity.setItemAmount(orderForm.getOrderItems().size());
//        //获取优惠上下文
        DiscountContext discountContext = new DiscountContext(orderForm.getItemDiscounts());
//        //存入总优惠
        BigDecimal totalDiscount = discountContext.getTotalDiscount();
        orderEntity.setTotalDiscount(totalDiscount);
        //计算费用
        FeeContext feeContext = new FeeContext(orderForm.getItemFees());
        BigDecimal totalFee = feeContext.getTotalFee();
        // 包邮
        orderEntity.setTotalFee(totalFee);
        //总的收费价=商品价格+收费价格
        orderEntity.setTotalPrice(orderForm.getTotalProductPrice().add(totalFee));
        //总的商品价格
        orderEntity.setTotalProductPrice(orderForm.getTotalProductPrice());
        //实际支付价格=商品价格+收费价格-优惠价格
        orderEntity.setRealPay(orderEntity.getTotalPrice().add(totalFee).subtract(totalDiscount));
        //新订单
        orderEntity.setStatus(OrderStatus.ORDER_NEW);
        //留言
        orderEntity.setComment(orderForm.getComment());
        //额外信息
        orderEntity.setExtra(JsonMapper.defaultMapper().toJson(orderForm.getExtraMap()));
        //创建时间
        orderEntity.setCreateAt(System.currentTimeMillis());
        //未删除
        orderEntity.setDeleted(false);

        List<OrderItem> orderItems = orderForm.getOrderItems().stream().map(r -> createOrderItem(r, discountContext, feeContext)).collect(Collectors.toList());

        orderEntity.addOrderItems(orderItems);

        if (orderItems.size() > 1) {
            orderEntity.setSubject(trimSubject(orderItems.get(0).getProductName()) + "等多个商品");
        } else {
            orderEntity.setSubject(trimSubject(orderItems.get(0).getProductName()));
        }

        return orderEntity;
    }

    private String trimSubject(String subject) {
        if (subject.length() > 10) {
            return subject.substring(0, 10) + "...";
        } else {
            return subject;
        }
    }

    /**
     * 创建 商城订单
     *
     * @param orderForm   orderForm
     * @param userAddress userAddress
     * @return ShopOrderEntity shop order entity
     */
    public ShopOrderEntity createShopOrderEntity(OrderForm orderForm, UserAddress userAddress) {
        ShopOrderEntity orderEntity = ShopOrderEntity.clone(createOrderEntity(orderForm));
        //readme 这里  outTradeNo 需要在 save 的时候进行完善
        ShipmentCreateForm shipmentCreateForm = new ShipmentCreateForm(userAddress, ShipmentType.DEFAULT, "-1");
        orderEntity.setShipmentCreateForm(shipmentCreateForm);
        return orderEntity;
    }


    /**
     * 创建优惠列表
     *
     * @param discountItems the discount items
     * @param orderItems    the order items
     * @return the list
     */
    public List<OrderItemDiscount> createOrderItemDiscounts(List<OrderForm.ItemDiscount> discountItems, List<OrderItem> orderItems) {
        List<OrderItemDiscount> orderDiscounts = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderDiscounts = discountItems
                    .stream()
                    .filter(discountItem -> discountItem.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0)
                    .map(discountItem -> this.createOrderItemDiscount(discountItem, orderItem)
                    ).collect(Collectors.toList());
        }
        return orderDiscounts;
    }

    /**
     * 创建 商品优惠 记录
     *
     * @param itemDiscount item's discount input form
     * @return OrderDiscount
     */
    private OrderItemDiscount createOrderItemDiscount(OrderForm.ItemDiscount itemDiscount, OrderItem orderItem) {
        if (itemDiscount != null) {
            OrderItemDiscount orderItemDiscount = new OrderItemDiscount();
            DiscountType discountType = itemDiscount.getDiscountType();
            orderItemDiscount.setType(discountType.getCode());
            orderItemDiscount.setDes(discountType.getDes());
            orderItemDiscount.setDiscountId(itemDiscount.getDiscountId());
            orderItemDiscount.setDiscount(itemDiscount.getDiscountPrice());
            orderItemDiscount.setNeedReturn(discountType.isNeedReturn());
            orderItemDiscount.setReturnType("");
            orderItemDiscount.setReturnStatus(false);
            orderItemDiscount.setReturnAt(0L);
            //orderId
            orderItemDiscount.setOrderId(orderItem.getOrderId());
            //orderItemId
            orderItemDiscount.setOrderItemId(orderItem.getId());
            orderItemDiscount.setExtras(JsonMapper.INSTANCE.toJson(itemDiscount.getExtraMap()));
            long now = System.currentTimeMillis();
            orderItemDiscount.setCreated(now);
            return orderItemDiscount;
        }
        return null;
    }


    /**
     * 创建 订单ITEM选项
     *
     * @param orderItemForm   orderItemForm
     * @param discountContext the discount context
     * @return OrderItem order item
     */
    public OrderItem createOrderItem(OrderItemForm orderItemForm, DiscountContext discountContext, FeeContext feeContext) {
        if (orderItemForm != null) {
            OrderItem orderItem = new OrderItem();
            Long productId = orderItemForm.getProductId();
            Long skuId = orderItemForm.getSkuId();
            //productId
            orderItem.setProductId(productId);
            orderItem.setProductName(orderItemForm.getProductName());
            orderItem.setProductCover(orderItemForm.getProductCover());
            orderItem.setProductType(orderItemForm.getProductType());
            //skuId
            orderItem.setSkuId(skuId);
            orderItem.setSkuName(orderItemForm.getSkuName());
            orderItem.setSkuCover(orderItemForm.getSkuCover());
            Integer number = orderItemForm.getNumber();
            orderItem.setNum(number);
            //过滤优惠
            BigDecimal filterDiscountPrice = discountContext.getFilterDiscount(productId, skuId);
            BigDecimal filterFee = feeContext.getFilterFee(productId, skuId);
            BigDecimal productPrice = orderItemForm.getProductPrice();
            //总价=商品价格*num+费用
            BigDecimal price = productPrice.multiply(BigDecimal.valueOf(number)).add(filterFee);
            orderItem.setPrice(price);
            orderItem.setFee(filterFee);
            orderItem.setProductPrice(productPrice);
            orderItem.setDiscount(filterDiscountPrice);
            //实际支付价格=商品价格+费用-优惠价格
            orderItem.setRealPay(price.add(filterFee).subtract(filterDiscountPrice));
            //后面添加
            orderItem.setOrderId(0L);
            orderItem.setExtra(JsonMapper.INSTANCE.toJson(orderItemForm.getExtraMap()));
            long now = System.currentTimeMillis();
            orderItem.setCreateAt(now);
            orderItem.setDeleted(false);
            return orderItem;
        }
        return null;
    }

    /**
     * 创建费用列表
     *
     * @param feeItems   the fee items
     * @param orderItems the order items
     * @return the list
     */
    public List<OrderItemFee> createOrderItemFees(List<OrderForm.ItemFee> feeItems, List<OrderItem> orderItems) {
        List<OrderItemFee> orderItemFees = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderItemFees = feeItems
                    .stream()
                    .filter(feeItem -> feeItem.getFee().compareTo(BigDecimal.ZERO) > 0)
                    .map(feeItem -> this.createOrderItemFee(feeItem, orderItem)
                    ).collect(Collectors.toList());
        }
        return orderItemFees;
    }

    private OrderItemFee createOrderItemFee(OrderForm.ItemFee itemFee, OrderItem orderItem) {
        if (itemFee != null) {
            OrderItemFee orderItemFee = new OrderItemFee();
            FeeType feeType = itemFee.getFeeType();
            orderItemFee.setType(feeType.getCode());
            orderItemFee.setFeeId(itemFee.getFeeId());
            orderItemFee.setFee(itemFee.getFee());
            orderItemFee.setNeedReturn(false);
            orderItemFee.setReturnType("");
            orderItemFee.setReturnStatus(false);
            orderItemFee.setReturnAt(0L);
            orderItemFee.setOrderId(orderItem.getOrderId());
            orderItemFee.setOrderItemId(orderItem.getId());
            orderItemFee.setExtras(JsonMapper.INSTANCE.toJson(itemFee.getExtraMap()));
            long now = System.currentTimeMillis();
            orderItemFee.setCreateAt(now);
            return orderItemFee;
        }
        return null;
    }
}
