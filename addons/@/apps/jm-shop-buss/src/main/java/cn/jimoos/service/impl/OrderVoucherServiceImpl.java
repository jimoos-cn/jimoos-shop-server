package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.constant.OrderStatus;
import cn.jimoos.constant.OrderVoucherStatus;
import cn.jimoos.dao.OrderMapper;
import cn.jimoos.dao.PaymentMapper;
import cn.jimoos.error.OrderError;
import cn.jimoos.form.offline.OfflinePayForm;
import cn.jimoos.model.Order;
import cn.jimoos.model.Payment;
import cn.jimoos.service.OrderService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.OrderVO;
import cn.jimoos.vo.order.OrderWithVoucherVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jimoos.dao.OrderVoucherMapper;
import cn.jimoos.model.OrderVoucher;
import cn.jimoos.service.OrderVoucherService;

@Service
public class OrderVoucherServiceImpl implements OrderVoucherService {

    @Resource
    private OrderVoucherMapper orderVoucherMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PaymentMapper paymentMapper;
    @Resource
    private OrderService orderService;

    @Override
    public void save(OrderVoucher record) {
        OrderVoucher orderVoucher = orderVoucherMapper.selectOneByOrderId(record.getOrderId());
        Order order = orderMapper.selectByPrimaryKey(record.getOrderId());
        String mapStr = order.getExtra();
        JSONObject jsonObject = JSONObject.parseObject(mapStr);
        Map<String, Object> map = jsonObject;
        if (map == null) {
            map = new HashMap<>();
        }
        // 添加上传凭证标志
        map.put("voucher", true);
        String extra = JSONObject.toJSONString(map);
        order.setExtra(extra);
        order.setUpdateAt(System.currentTimeMillis());
        orderMapper.updateByPrimaryKey(order);
        if (orderVoucher == null) {
            orderVoucherMapper.insert(record);
        } else {
            record.setId(orderVoucher.getId());
            orderVoucherMapper.updateByPrimaryKey(record);
        }
    }

    @Override
    public void pass(OfflinePayForm offlinePayForm) throws BussException {
        OrderVoucher orderVoucher = orderVoucherMapper.selectByPrimaryKey(offlinePayForm.getId());
        if (orderVoucher == null) {
            throw new BussException(OrderError.ORDER_PAYMENT_NOT_FOUND);
        }
        orderVoucher.setStatus(OrderVoucherStatus.PASS);
        orderVoucher.setUpdateAt(System.currentTimeMillis());


        Order order = orderMapper.selectByPrimaryKey(orderVoucher.getOrderId());
        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        if (order.getStatus() == OrderStatus.CANCEL) {
            throw new BussException(OrderError.ORDER_IS_CANCELED);
        }
        order.setStatus(OrderStatus.PAID);
        order.setUpdateAt(System.currentTimeMillis());
        Payment payment = paymentMapper.findOneByOutTradeNo(order.getOrderNum());
        if (payment == null) {
            throw new BussException(OrderError.ORDER_PAYMENT_NOT_FOUND);
        }
        if (payment.getPaid()) {
            throw new BussException(OrderError.ORDER_IS_PAID);
        }
        payment.setPaid(true);
        payment.setUpdateAt(System.currentTimeMillis());
        // 更新
        orderMapper.updateByPrimaryKey(order);
        paymentMapper.updateByPrimaryKey(payment);
        orderVoucherMapper.updateByPrimaryKey(orderVoucher);
    }

    @Override
    public void fail(OfflinePayForm offlinePayForm) throws BussException {
        OrderVoucher orderVoucher = orderVoucherMapper.selectByPrimaryKey(offlinePayForm.getId());
        if (orderVoucher == null) {
            throw new BussException(OrderError.ORDER_PAYMENT_NOT_FOUND);
        }
        orderVoucher.setStatus(OrderVoucherStatus.FAIL);
        orderVoucher.setUpdateAt(System.currentTimeMillis());
        orderVoucher.setReason(offlinePayForm.getReason());
        orderVoucherMapper.updateByPrimaryKey(orderVoucher);
    }

    @Override
    public OrderVoucher getOne(Long orderId) {
        return orderVoucherMapper.selectOneByOrderId(orderId);
    }

    @Override
    public Page<OrderVoucher> getPage(OfflinePayForm OfflinePayForm) {
        Long count = orderVoucherMapper.selectPageCount(OfflinePayForm);
        if (count > 0) {
            List<OrderVoucher> list = orderVoucherMapper.selectPage(OfflinePayForm);
            return Page.create(count, list);
        }
        return Page.empty();
    }

    @Override
    public OrderWithVoucherVO getDetail(Long id) throws BussException {
        OrderVoucher orderVoucher = orderVoucherMapper.selectByPrimaryKey(id);
        if (orderVoucher == null) {
            throw new BussException(OrderError.ORDER_PAYMENT_NOT_FOUND);
        }
        OrderVO one = orderService.getOrderDetails(orderVoucher.getOrderId());
        OrderWithVoucherVO vo = new OrderWithVoucherVO();
        BeanUtils.copyProperties(one, vo);
        vo.setVoucher(orderVoucher);
        return vo;
    }
}


