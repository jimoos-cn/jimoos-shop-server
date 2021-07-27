package cn.jimoos.service;

import cn.jimoos.model.OrderRefund;

public interface OrderRefundService {


    int deleteByPrimaryKey(Long id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
}



