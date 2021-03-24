package cn.jimoos.factory;

import cn.jimoos.entity.RouteEntity;
import cn.jimoos.form.RouteForm;
import cn.jimoos.repository.RouteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-01-19 14:14.
 */
@Component
public class RouteFactory {
    @Resource
    RouteRepository routeRepository;

    public RouteEntity create(RouteForm routeForm) {
        RouteEntity routeEntity = new RouteEntity(routeRepository);
        BeanUtils.copyProperties(routeForm, routeEntity);
        routeEntity.setUpdateAt(System.currentTimeMillis());
        routeEntity.setDeleted(false);
        routeEntity.setCreateAt(System.currentTimeMillis());
        return routeEntity;
    }
}
