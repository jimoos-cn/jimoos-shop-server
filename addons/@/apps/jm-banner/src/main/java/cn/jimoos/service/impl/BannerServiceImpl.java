package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.BannerMapper;
import cn.jimoos.dic.BannerStatus;
import cn.jimoos.entity.BannerEntity;
import cn.jimoos.entity.RouteEntity;
import cn.jimoos.factory.BannerFactory;
import cn.jimoos.form.BannerForm;
import cn.jimoos.form.BannerQueryForm;
import cn.jimoos.model.Banner;
import cn.jimoos.repository.BannerRepository;
import cn.jimoos.repository.RouteRepository;
import cn.jimoos.service.BannerService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.BannerVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-01-19 14:01.
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Resource
    BannerFactory bannerFactory;
    @Resource
    BannerRepository bannerRepository;
    @Resource
    RouteRepository routeRepository;
    @Resource
    BannerMapper bannerMapper;

    @Override
    public BannerVO addBanner(BannerForm bannerForm) throws BussException {
        BannerEntity bannerEntity = bannerFactory.create(bannerForm);
        bannerEntity.setPath(router4mat(bannerEntity));
        bannerRepository.save(bannerEntity);
        return entityToVo(bannerEntity);
    }

    @Override
    public BannerVO updateBanner(Long bannerId, BannerForm bannerForm) throws BussException {
        BannerEntity bannerEntity = bannerRepository.findById(bannerId);
        BeanUtils.copyProperties(bannerForm, bannerEntity);
        bannerEntity.setPath(router4mat(bannerEntity));
        bannerRepository.update(bannerEntity);
        return entityToVo(bannerEntity);
    }

    @Override
    public BannerVO getBannerById(Long bannerId) throws BussException {
        BannerEntity bannerEntity = bannerRepository.findById(bannerId);
        return entityToVo(bannerEntity);
    }

    @Override
    public int upBanner(Long bannerId) throws BussException {
        BannerEntity bannerEntity = bannerRepository.findById(bannerId);
        bannerEntity.setUp();
        return bannerRepository.update(bannerEntity);
    }

    @Override
    public int downBanner(Long bannerId) throws BussException {
        BannerEntity bannerEntity = bannerRepository.findById(bannerId);
        bannerEntity.setDown();
        return bannerRepository.update(bannerEntity);
    }

    @Override
    public int deleteBanner(Long bannerId) throws BussException {
        BannerEntity bannerEntity = bannerRepository.findById(bannerId);
        bannerEntity.setDelete();
        return bannerRepository.update(bannerEntity);
    }

    @Override
    public Page<BannerVO> backQuery(BannerQueryForm bannerQueryForm) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("title", bannerQueryForm.getTitle());
        map.put("position", bannerQueryForm.getPosition());
        map.put("status", bannerQueryForm.getStatus());
        PageHelper.offsetPage(bannerQueryForm.getOffset(), bannerQueryForm.getLimit());
        List<Banner> banners = bannerMapper.backQuery(map);
        PageInfo<Banner> pageInfo = new PageInfo<>(banners);
        List<BannerVO> datas = banners.stream().map(
                ls -> {
                    BannerVO vo = new BannerVO();
                    BeanUtils.copyProperties(ls, vo);
                    return vo;
                }
        ).collect(Collectors.toList());
        return Page.create(pageInfo.getTotal(), datas);
    }

    @Override
    public List<BannerVO> query(Integer position) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("position", position);
        map.put("status", BannerStatus.UP);
        List<Banner> banners = bannerMapper.backQuery(map);
        return banners.stream().map(
                ls -> {
                    BannerVO vo = new BannerVO();
                    BeanUtils.copyProperties(ls, vo);
                    return vo;
                }
        ).collect(Collectors.toList());
    }

    private BannerVO entityToVo(BannerEntity bannerEntity) {
        BannerVO bannerVo = new BannerVO();
        BeanUtils.copyProperties(bannerEntity, bannerVo);
        return bannerVo;
    }

    /**
     * 拼接返回的路由
     *
     * @param bannerEntity
     * @return
     */
    private String router4mat(BannerEntity bannerEntity) throws BussException {
        if (bannerEntity.getRouteId() == 0) {
            return null;
        } else {
            RouteEntity routeEntity = routeRepository.findById(bannerEntity.getRouteId());
            String routerJson = routeEntity.getRoute();
            return String.format(routerJson, bannerEntity.getTargetId());
        }
    }
}
