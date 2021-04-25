package cn.jimoos.dao;

import cn.jimoos.model.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:55.
 */

@Mapper
public interface PaymentMapper {
    int insert(Payment record);

    int updateByPrimaryKey(Payment record);

    Payment findOneByOutTradeNo(@Param("outTradeNo")String outTradeNo);
}
