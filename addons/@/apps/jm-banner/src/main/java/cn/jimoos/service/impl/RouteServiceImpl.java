package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.RouteEntity;
import cn.jimoos.factory.RouteFactory;
import cn.jimoos.form.RouteForm;
import cn.jimoos.repository.RouteRepository;
import cn.jimoos.service.RouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-01-19 14:01.
 */
@Service
public class RouteServiceImpl implements RouteService {
    @Resource
    RouteFactory routeFactory;
    @Resource
    RouteRepository routeRepository;

    @Override
    public void addRoute(RouteForm routeForm) {
        RouteEntity routeEntity = routeFactory.create(routeForm);
        routeRepository.save(routeEntity);
    }

    @Override
    public void updateRoute(Long routeId, RouteForm routeForm) throws BussException {
        RouteEntity routeEntity = routeRepository.findById(routeId);
        BeanUtils.copyProperties(routeForm, routeEntity);
        routeEntity.setUpdateAt(System.currentTimeMillis());
        routeRepository.update(routeEntity);
    }

    @Override
    public void deleteRoute(Long routeId) throws BussException {
        RouteEntity routeEntity = routeRepository.findById(routeId);
        routeEntity.setDeleted(true);
        routeEntity.setUpdateAt(System.currentTimeMillis());
        routeRepository.update(routeEntity);
    }
}
