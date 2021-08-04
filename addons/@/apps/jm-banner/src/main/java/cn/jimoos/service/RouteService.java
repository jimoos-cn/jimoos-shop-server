package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.RouteForm;
import cn.jimoos.route.model.Route;

import java.util.List;


/**
 * route 路由管理
 *
 * @author :keepcleargas
 * @date :2021-01-19 14:01.
 */
public interface RouteService {
    /**
     * 添加route
     *
     * @param routeForm
     */
    void addRoute(RouteForm routeForm);

    /**
     * 修改route
     *
     * @param routeId
     * @param routeForm
     * @throws BussException
     */
    void updateRoute(Long routeId, RouteForm routeForm) throws BussException;

    /**
     * 删除route
     *
     * @param routeId
     * @throws BussException
     */
    void deleteRoute(Long routeId) throws BussException;

    /**
     * 获取route
     * @param routeForm
     * @return
     */
    List<Route> queryRoute(RouteForm routeForm);
}
