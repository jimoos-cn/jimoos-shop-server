package cn.jimoos.dao;

import cn.jimoos.model.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-27 07:41.
 */
@Mapper
public interface CouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    Coupon selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Coupon record);

    int batchInsert(@Param("list") List<Coupon> list);

    /**
     * 批量查询 优惠券
     *
     * @param idCollection
     * @return
     */
    List<Coupon> findByIdIn(@Param("idCollection") Collection<Long> idCollection);

    /**
     * coupon id 软删除
     *
     * @param id coupon id
     * @return affectNum
     */
    int setDeletedTrue(@Param("id") Long id);

    /**
     * 查询最新的一次 code 兑换优惠券
     *
     * @param code 兑换码
     * @return 优惠券
     */
    Coupon findFirstByCode(@Param("code") String code);

    /**
     * 查询 Coupon 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return List<Coupon>
     */
    List<Coupon> queryTable(Map<String, Object> qm);

    /**
     * 查询 Coupon 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}