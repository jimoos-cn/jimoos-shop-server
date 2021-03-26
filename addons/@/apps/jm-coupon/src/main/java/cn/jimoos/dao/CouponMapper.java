package cn.jimoos.dao;

import cn.jimoos.model.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-26 21:36.
 */
@Mapper
public interface CouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    Coupon selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Coupon record);

    int batchInsert(@Param("list") List<Coupon> list);

    /**
     * 查询最新的一次 code 兑换优惠券
     *
     * @param code
     * @return
     */
    Coupon findFirstByCode(@Param("code") String code);
}