package cn.jimoos.factory;

import cn.jimoos.entity.OrderCartEntity;
import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.repository.OrderCartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-07 17:55.
 */
@Component
public class OrderCartFactory {
    @Resource
    OrderCartRepository orderCartRepository;

    /**
     * 创建 OrderCartEntity
     *
     * @param orderCartForm order cart form
     * @return OrderCartEntity
     */
    public OrderCartEntity create(OrderCartForm orderCartForm) {
        Long now = System.currentTimeMillis();
        OrderCartEntity orderCartEntity = new OrderCartEntity(orderCartRepository);
        BeanUtils.copyProperties(orderCartForm, orderCartEntity);
        orderCartEntity.setCreateAt(now);
        orderCartEntity.setUpdateAt(now);
        orderCartEntity.setDeleted(false);
        return orderCartEntity;
    }
}
