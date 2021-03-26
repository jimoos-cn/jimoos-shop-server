package cn.jimoos.dao;

import cn.jimoos.model.CouponRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-11-30 15:35.
 */

@Mapper
public interface CouponRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponRecord record);
    
    CouponRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CouponRecord record);

    int batchInsert(@Param("list") List<CouponRecord> list);

    CouponRecord findOneByCouponIdAndUserId(@Param("couponId") Long couponId, @Param("userId") Long userId);
}