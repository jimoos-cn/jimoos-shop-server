package cn.jimoos.dao;

import cn.jimoos.form.offline.OfflinePayForm;import cn.jimoos.model.OrderVoucher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderVoucherMapper {
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderVoucher record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OrderVoucher selectByPrimaryKey(Long id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderVoucher record);

    int batchInsert(@Param("list") List<OrderVoucher> list);

    OrderVoucher selectOneByOrderId(@Param("orderId") Long orderId);

    /**
     * 获取 分页记录个数
     *
     * @param offlinePayForm
     * @return
     */
    Long selectPageCount(OfflinePayForm offlinePayForm);

    /**
     * 后台获取分页
     *
     * @param offlinePayForm
     * @return
     */
    List<OrderVoucher> selectPage(OfflinePayForm offlinePayForm);
}