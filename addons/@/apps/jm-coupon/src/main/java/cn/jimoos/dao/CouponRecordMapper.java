package cn.jimoos.dao;

import cn.jimoos.model.CouponRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取用户的 某优惠券 领取记录
     *
     * @param couponId 优惠券 ID
     * @param userId   用户 ID
     * @return CouponRecord
     */
    CouponRecord findOneByCouponIdAndUserId(@Param("couponId") Long couponId, @Param("userId") Long userId);

    /**
     * 查询 用户 CouponRecord 列表
     *
     * @param qm ,支持 ${userId} 的 倒序分页查询
     * @return List<CouponRecord>
     */
    List<CouponRecord> queryUserRecords(Map<String, Object> qm);

    /**
     * 查询满足条件的优惠券列表
     *
     * @param qm ,支持 ${userId}/${beyond} 的 最优解倒序分页查询
     * @return List<CouponRecord>
     */
    List<CouponRecord> querySatisfyRecords(Map<String, Object> qm);

    /**
     * 获取 最优的优惠券
     *
     * @param userId user id
     * @param beyond 满多少钱
     * @return 优惠券
     */
    CouponRecord findBestOneCouponRecord(@Param("userId") Long userId, @Param("beyond") BigDecimal beyond);

    /**
     * 查询 CouponRecord 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${status} 的 倒序分页查询
     * @return List<CouponRecord>
     */
    List<CouponRecord> queryTable(Map<String, Object> qm);

    /**
     * 查询 CouponRecord 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${status} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);

    /**
     * 统计 couponID 领取数目
     *
     * @param couponId
     * @return
     */
    Long countByCouponId(@Param("couponId") Long couponId);
}