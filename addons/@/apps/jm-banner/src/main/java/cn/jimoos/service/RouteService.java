package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.RouteForm;


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
}
