package cn.jimoos.vo;

import cn.jimoos.model.OrderItem;
import cn.jimoos.model.OrderRefund;
import lombok.Data;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/27 14:28:01
 * @description
 */
@Data
public class OrderRefundVO extends OrderRefund {
    /**
     * 订单的商品
     */
    List<OrderItem> orderItems;

}
