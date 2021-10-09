package cn.jimoos.service;


import cn.jimoos.vo.StatisticsVO;

/**
 * @author SiletFlower
 * @date 2021/10/8 20:34:51
 * @description
 */
public interface StatisticsService {
    /**
     * 获取近7日统计数据
     */
    StatisticsVO getSevenDayStatics();
}
