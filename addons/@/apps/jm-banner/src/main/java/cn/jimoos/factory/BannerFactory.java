package cn.jimoos.factory;

import cn.jimoos.entity.BannerEntity;
import cn.jimoos.form.BannerForm;
import cn.jimoos.repository.BannerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-01-19 14:14.
 */
@Component
public class BannerFactory {
    @Resource
    BannerRepository bannerRepository;

    public BannerEntity create(BannerForm bannerForm) {
        BannerEntity bannerEntity = new BannerEntity(bannerRepository);
        BeanUtils.copyProperties(bannerForm, bannerEntity);
        bannerEntity.setDown();
        bannerEntity.setDeleted(false);
        bannerEntity.setCreateAt(System.currentTimeMillis());
        return bannerEntity;
    }

}
