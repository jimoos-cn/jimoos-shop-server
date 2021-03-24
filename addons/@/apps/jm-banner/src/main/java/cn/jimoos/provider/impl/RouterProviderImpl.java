package cn.jimoos.provider.impl;

import cn.jimoos.dao.RouteMapper;
import cn.jimoos.route.model.Route;
import cn.jimoos.route.provider.RouterProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 提供全局的 Route Path 跳转
 *
 * @author :keepcleargas
 * @date :2021-01-25 15:01.
 */
@Component
public class RouterProviderImpl implements RouterProvider {
    @Resource
    RouteMapper routeMapper;


    @Override
    public Route byId(Long id) {
        Route route = routeMapper.selectByPrimaryKey(id);
        return route == null || route.getDeleted() ? null : route;
    }

    @Override
    public List<Route> byIds(Collection<Long> ids) {
        List<Route> routeList = routeMapper.findByIds(ids);
        return routeList;
    }
}
