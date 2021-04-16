package cn.jimoos.cron;

import cn.jimoos.config.OrderProperties;
import cn.jimoos.dao.OrderMapper;
import cn.jimoos.dic.OrderStatus;
import cn.jimoos.form.order.CancelForm;
import cn.jimoos.model.Order;
import cn.jimoos.service.OrderComposeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单定时器
 *
 * @author :keepcleargas
 * @date :2021-04-15 17:10.
 */
@Component
@Slf4j
public class OrderJob {
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderComposeService orderComposeService;
    @Resource
    OrderProperties orderProperties;

    /**
     * 5分钟执行一次 默认60分钟内 未支付的订单 取消掉。
     */
    @Scheduled(fixedRate = 300000)
    public void resetUnpaidOrders() {
        log.info("Cancel Unpaid Order Job execute at time is {}", LocalDateTime.now());
        cancelAllExpiredOrders();
    }

    /**
     * 取消过期订单
     */
    private void cancelAllExpiredOrders() {
        //todo 订单量大的话 需要优化成 分页
        //默认 为 60 分钟，s为单位
        Long expired = System.currentTimeMillis() - orderProperties.getOrderExpiredTime() * 1000L;
        //获取待支付 且过期的订单
        List<Order> orders = orderMapper.selectUnpaidOrders(expired, OrderStatus.ORDER_NEW);
        for (Order order : orders) {
            CancelForm cancelForm = new CancelForm();
            cancelForm.setOrderId(order.getId());
            orderComposeService.cancelOrderExceptionWrapper(cancelForm);
        }
    }
}
