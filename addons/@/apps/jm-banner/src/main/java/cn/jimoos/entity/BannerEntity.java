package cn.jimoos.entity;


import cn.jimoos.common.exception.BussException;
import cn.jimoos.dic.BannerStatus;
import cn.jimoos.model.Banner;
import cn.jimoos.repository.BannerRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * banner
 *
 * @author :keepcleargas
 * @date :2021-01-19 11:32.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerEntity extends Banner {
    /**
     * The banner repository.
     */
    protected BannerRepository bannerRepository;


    /**
     * Instantiates a new banner entity.
     */
    public BannerEntity() {
    }

    /**
     * Instantiates a new banner entity.
     *
     * @param bannerRepository the banner repository
     */
    public BannerEntity(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    /**
     * 设置路由
     */
    public void setPath(String route) throws BussException {
        this.setPaths(route);
        this.setUpdateAt(System.currentTimeMillis());
    }


    /**
     * Sets up.
     */
    public void setUp() {
        this.setStatus(BannerStatus.UP);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * Sets down.
     */
    public void setDown() {
        this.setStatus(BannerStatus.DOWN);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * Sets delete.
     */
    public void setDelete() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
