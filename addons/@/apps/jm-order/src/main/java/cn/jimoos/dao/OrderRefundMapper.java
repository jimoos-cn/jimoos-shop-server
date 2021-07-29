package cn.jimoos.dao;
import cn.jimoos.form.order.be.BeRefundQueryForm;

import cn.jimoos.model.OrderRefund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderRefundMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderRefund record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(OrderRefund record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OrderRefund selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OrderRefund record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderRefund record);


    List<OrderRefund> findRefundByParamForPage(BeRefundQueryForm beRefundQueryForm);
}
