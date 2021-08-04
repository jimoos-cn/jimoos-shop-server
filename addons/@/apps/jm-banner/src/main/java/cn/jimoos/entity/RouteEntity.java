package cn.jimoos.entity;


import cn.jimoos.form.RouteForm;
import cn.jimoos.repository.RouteRepository;
import cn.jimoos.route.model.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * route
 *
 * @author :keepcleargas
 * @date :2021-01-19 11:32.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RouteEntity extends Route {
    /**
     * The route repository.
     */
    protected RouteRepository routeRepository;

    /**
     * Instantiates a new route entity.
     */
    public RouteEntity() {
    }

    /**
     * Instantiates a new route entity.
     *
     * @param routeRepository the route repository
     */
    public RouteEntity(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    /**
     * Sets deleted.
     */
    public void setDeleted() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 查询所有route
     */
    public List<Route> findAllRoute(RouteForm routeForm) {
        this.setType(routeForm.getType());
        this.setDescription(routeForm.getDescription());
        this.setRoute(routeForm.getRoute());
        return routeRepository.findAllByParam(this);
    }
}
