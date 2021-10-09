package cn.jimoos.rest.be;

import cn.jimoos.service.StatisticsService;
import cn.jimoos.vo.StatisticsVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/10/8 20:18:13
 * @description
 */
@RestController
@RequestMapping("/bAdmin/v1/statistics")
public class BeStatisticsApi {

    @Resource
    StatisticsService statisticsService;

    @GetMapping(produces = "application/json; charset=utf-8")
    public StatisticsVO getStatistics(){
        return statisticsService.getSevenDayStatics();
    }
    
}
