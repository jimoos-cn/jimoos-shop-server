package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.RouteMapper;
import cn.jimoos.entity.RouteEntity;
import cn.jimoos.error.RouteError;
import cn.jimoos.route.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Banner repository.
 *
 * @author :keepcleargas
 * @date :2021-01-19 11:51.
 */
@Repository
@Slf4j
public class RouteRepository {
    /**
     * The Route mapper.
     */
    @Resource
    RouteMapper routeMapper;


    /**
     * Save.
     *
     * @param routeEntity the route entity
     */
    public void save(RouteEntity routeEntity) {
        routeMapper.insertSelective(routeEntity);
    }

    /**
     * update.
     *
     * @param routeEntity the route entity
     */
    public void update(RouteEntity routeEntity) {
        routeMapper.updateByPrimaryKey(routeEntity);
    }

    /**
     * Wrapper route entity.
     *
     * @param route the route
     * @return the route entity
     */
    public RouteEntity wrapper(Route route) {
        RouteEntity routeEntity = new RouteEntity(this);
        BeanUtils.copyProperties(route, routeEntity);
        return routeEntity;
    }

    /**
     * 根据 id 获取route
     *
     * @param routeId routerId
     * @return RouteEntity route route
     * @throws BussException the buss exception
     */
    public RouteEntity findById(Long routeId) throws BussException {
        Route route = routeMapper.selectByPrimaryKey(routeId);
        if (route == null) {
            throw new BussException(RouteError.ROUTE_NOT_FOUND);
        }
        return wrapper(route);
    }

    public List<Route> findAllByParam(RouteEntity routeEntity) {
        return routeMapper.findAllByParam(routeEntity);
    }
}
