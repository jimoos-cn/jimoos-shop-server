package cn.jimoos.service.impl;

import cn.jimoos.dao.OrderCartMapper;
import cn.jimoos.entity.OrderCartEntity;
import cn.jimoos.factory.OrderCartFactory;
import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.form.cart.OrderCartIdsForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.repository.OrderCartRepository;
import cn.jimoos.service.OrderCartService;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Resource
    OrderCartMapper orderCartMapper;

    @Override
    public OrderCart saveToCart(OrderCartForm orderCartForm) {
        OrderCartEntity orderCartEntity = orderCartRepository.findOneByUserIdAndSkuId(orderCartForm.getUserId(), orderCartForm.getSkuId());

        if (orderCartEntity == null) {
            orderCartEntity = orderCartFactory.create(orderCartForm);

            //保存
            orderCartRepository.save(orderCartEntity);
        } else {
            orderCartEntity.setNum(orderCartForm.getNum());
            orderCartEntity.setChecked(orderCartForm.getChecked());
            orderCartEntity.setUpdateAt(System.currentTimeMillis());
            //保存
            orderCartRepository.save(orderCartEntity);
        }
        return orderCartEntity;
    }

    @Override
    public void remove(OrderCartIdsForm orderCartIdForm) {
        orderCartRepository.deleteByIds(orderCartIdForm.getUserId(), orderCartIdForm.getCartIds());
    }

    @Override
    public void checked(OrderCartIdsForm orderCartIdForm) {
        if (CollectionUtils.isEmpty(orderCartIdForm.getCartIds())) {
            return;
        }
        orderCartRepository.updateCheckedStatus(orderCartIdForm.getUserId(), orderCartIdForm.getCartIds());
    }

    @Override
    public void uncheck(OrderCartIdsForm orderCartIdForm) {
        if (CollectionUtils.isEmpty(orderCartIdForm.getCartIds())) {
            return;
        }
        orderCartRepository.updateUnCheckStatus(orderCartIdForm.getUserId(), orderCartIdForm.getCartIds());
    }

    @Override
    public List<OrderCart> getUserOrderCarts(AbstractUserPageForm4L form4L) {
        return orderCartMapper.findByUserId(form4L.getUserId(), form4L.getOffset(), form4L.getLimit());
    }
}
