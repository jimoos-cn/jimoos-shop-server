package cn.jimoos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.jimoos.model.OrderRefund;
import cn.jimoos.dao.OrderRefundMapper;
import cn.jimoos.service.OrderRefundService;

@Service
public class OrderRefundServiceImpl implements OrderRefundService {

    @Resource
    private OrderRefundMapper orderRefundMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderRefundMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderRefund record) {
        return orderRefundMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderRefund record) {
        return orderRefundMapper.insertSelective(record);
    }

    @Override
    public OrderRefund selectByPrimaryKey(Long id) {
        return orderRefundMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderRefund record) {
        return orderRefundMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderRefund record) {
        return orderRefundMapper.updateByPrimaryKey(record);
    }

}



