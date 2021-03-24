package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.RouteForm;
import cn.jimoos.service.RouteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-23 13:45.
 */
@RestController
@RequestMapping("/bAdmin/v1/routes")
public class BeRouteApi {
    @Resource
    RouteService routeService;

    /**
     * 新增route
     *
     * @param routeForm route Form
     */
    @PostMapping(value = "", produces = "application/json; charset=utf-8")
    public void addRoute(@ModelAttribute RouteForm routeForm) {
        routeService.addRoute(routeForm);
    }

    /**
     * 修改route
     *
     * @param routeId   route Id
     * @param routeForm route Form
     * @throws BussException BannerError.BANNER_NOT_FOUND
     */
    @PostMapping(value = "/{routeId}", produces = "application/json; charset=utf-8")
    public void updateRoute(@PathVariable("routeId") Long routeId, @ModelAttribute RouteForm routeForm) throws BussException {
        routeService.updateRoute(routeId, routeForm);
    }

    /**
     * 删除route
     *
     * @param routeId route Id
     * @throws BussException BannerError.BANNER_NOT_FOUND
     */
    @DeleteMapping(value = "/{routeId}", produces = "application/json; charset=utf-8")
    public void deleteRoute(@PathVariable("routeId") Long routeId) throws BussException {
        routeService.deleteRoute(routeId);
    }
}
