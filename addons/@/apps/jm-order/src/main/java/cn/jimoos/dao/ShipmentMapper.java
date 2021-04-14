package cn.jimoos.dao;

import cn.jimoos.model.Shipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * The interface Shipment mapper.
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Mapper
public interface ShipmentMapper {
    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(Shipment record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(Shipment record);

    /**
     * Find one by type and out trade no shipment.
     *
     * @param type       the type
     * @param outTradeNo the out trade no
     * @return the shipment
     */
    Shipment findOneByTypeAndOutTradeNo(@Param("type") Integer type, @Param("outTradeNo") String outTradeNo);

    /**
     * Find list by type And outTradeNo list
     *
     * @param type                 配送类别
     * @param outTradeNoCollection 外部编号列表
     * @return this list
     */
    List<Shipment> findByTypeAndOutTradeNoIn(@Param("type") Integer type, @Param("outTradeNoCollection") Collection<String> outTradeNoCollection);


}
