package cn.jimoos.service.impl;

import cn.jimoos.dao.OrderMapper;
import cn.jimoos.dao.UserMapper;
import cn.jimoos.service.StatisticsService;
import cn.jimoos.vo.StatisticsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/10/8 20:35:19
 * @description
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    UserMapper userMapper;
    @Resource
    OrderMapper orderMapper;

    private static final Integer MAX = 7;
    
    @Override
    public StatisticsVO getSevenDayStatics() {
        StatisticsVO vo = new StatisticsVO();
        long userCount = userMapper.queryTableCount(null);
        long orderCount = orderMapper.queryTableCount(null);
        BigDecimal sales = orderMapper.getSales(null);
        vo.setUserAllCount(userCount);
        vo.setOrderAllCount(orderCount);
        vo.setAllSales(sales);
        List<StatisticsVO.Statistics> list = new ArrayList<>();
        for (int day = 0; day < MAX; day++) {
            long start = LocalDateTime.of(LocalDate.now().minusDays(day), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long end = LocalDateTime.of(LocalDate.now().minusDays(day), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            StatisticsVO.Statistics statistics = new StatisticsVO.Statistics();
            Map qm = new HashMap<>();
            qm.put("startTime", start);
            qm.put("endTime", end);
            long dayUserCount = userMapper.queryTableCount(qm);
            long dayOrderCount = orderMapper.queryTableCount(qm);
            BigDecimal daySales = orderMapper.getSales(qm);
            statistics.setStartTime(start);
            statistics.setEndTime(end);
            statistics.setOrderCount(dayOrderCount);
            statistics.setSales(daySales);
            statistics.setUserCount(dayUserCount);
            list.add(statistics);
        }
        vo.setDay7(list);
        return vo;
    }
}
