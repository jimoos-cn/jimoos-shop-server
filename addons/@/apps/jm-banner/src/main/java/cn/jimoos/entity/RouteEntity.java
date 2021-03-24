package cn.jimoos.entity;


import cn.jimoos.repository.RouteRepository;
import cn.jimoos.route.model.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
