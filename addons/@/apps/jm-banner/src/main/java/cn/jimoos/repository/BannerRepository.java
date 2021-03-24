package cn.jimoos.repository;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.BannerMapper;
import cn.jimoos.entity.BannerEntity;
import cn.jimoos.error.BannerError;
import cn.jimoos.model.Banner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * The type Banner repository.
 *
 * @author :keepcleargas
 * @date :2021-01-19 11:51.
 */
@Repository
@Slf4j
public class BannerRepository {
    /**
     * The Banner mapper.
     */
    @Resource
    BannerMapper bannerMapper;


    /**
     * Save.
     *
     * @param bannerEntity the banner entity
     */
    public int save(BannerEntity bannerEntity) {
        return bannerMapper.insertSelective(bannerEntity);
    }

    /**
     * update.
     *
     * @param bannerEntity the banner entity
     */
    public int update(BannerEntity bannerEntity) {
        return bannerMapper.updateByPrimaryKey(bannerEntity);
    }

    /**
     * Wrapper banner entity.
     *
     * @param banner the banner
     * @return the banner entity
     */
    public BannerEntity wrapper(Banner banner) {
        BannerEntity bannerEntity = new BannerEntity(this);
        BeanUtils.copyProperties(banner, bannerEntity);
        return bannerEntity;
    }

    /**
     * 根据 id 获取banner
     *
     * @param bannerId bannerId
     * @return BannerEntity banner entity
     * @throws BussException the buss exception
     */
    public BannerEntity findById(Long bannerId) throws BussException {
        Banner banner = bannerMapper.selectByPrimaryKey(bannerId);

        if (banner == null) {
            throw new BussException(BannerError.BANNER_NOT_FOUND);
        }
        return wrapper(banner);
    }

}
