package cn.jimoos.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/10/8 20:29:10
 * @description 统计数据
 */
@Data
public class StatisticsVO {
    /**
     * 总用户数
     */
    private Long userAllCount;

    /**
     * 总订单数
     */
    private Long orderAllCount;

    /**
     * 总销售额
     */
    private BigDecimal allSales;

    /**
     * 7日内统计数据
     */
    private List<Statistics> day7;
    
    @Data
    @NoArgsConstructor
    public static class Statistics{
        /**
         * 增加用户
         */
        private Long userCount;

        /**
         * 增加订单
         */
        private Long orderCount;

        /**
         * 销售额
         */
        private BigDecimal sales;

        /**
         * 开始时间
         */
        private Long startTime;

        /**
         * 结束时间
         */
        private Long endTime;
    }
}
