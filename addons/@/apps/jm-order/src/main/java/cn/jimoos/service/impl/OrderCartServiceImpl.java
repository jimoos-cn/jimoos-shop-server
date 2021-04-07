package cn.jimoos.service.impl;

import cn.jimoos.entity.OrderCartEntity;
import cn.jimoos.factory.OrderCartFactory;
import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.form.cart.OrderCartIdForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.repository.OrderCartRepository;
import cn.jimoos.service.OrderCartService;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-07 17:55.
 */
@Service
public class OrderCartServiceImpl implements OrderCartService {
    @Resource
    OrderCartFactory orderCartFactory;
    @Resource
    OrderCartRepository orderCartRepository;

    @Override
    public OrderCart saveToCart(OrderCartForm orderCartForm) {
        OrderCartEntity orderCartEntity = orderCartFactory.create(orderCartForm);

        //保存
        orderCartRepository.save(orderCartEntity);
        return orderCartEntity;
    }

    @Override
    public void remove(OrderCartIdForm orderCartIdForm) {
        orderCartRepository.deleteByIds(orderCartIdForm.getUserId(), orderCartIdForm.getCartIds());
    }

    @Override
    public void checked(OrderCartIdForm orderCartIdForm) {

    }

    @Override
    public void uncheck(OrderCartIdForm orderCartIdForm) {

    }

    @Override
    public List<OrderCart> getUserOrderCarts(AbstractUserPageForm4L form4L) {
        return null;
    }
}
