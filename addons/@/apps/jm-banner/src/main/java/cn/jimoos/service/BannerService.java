package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.BannerForm;
import cn.jimoos.form.BannerQueryForm;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.BannerVO;

import java.util.List;


/**
 * banner
 *
 * @author :keepcleargas
 * @date :2021-01-19 14:01.
 */
public interface BannerService {
    /**
     * 添加banner
     *
     * @param bannerForm banner form
     * @return BannerVO
     * @throws BussException
     */
    BannerVO addBanner(BannerForm bannerForm) throws BussException;

    /**
     * 修改banner
     *
     * @param bannerId
     * @param bannerForm
     * @return BannerVO
     * @throws BussException
     */
    BannerVO updateBanner(Long bannerId, BannerForm bannerForm) throws BussException;

    /**
     * 根据id获取详情
     *
     * @param bannerId
     * @return BannerVO
     * @throws BussException
     */
    BannerVO getBannerById(Long bannerId) throws BussException;

    /**
     * 上架banner
     *
     * @param bannerId banner ID
     * @return affectNum
     * @throws BussException
     */
    int upBanner(Long bannerId) throws BussException;

    /**
     * 下架banner
     *
     * @param bannerId banner ID
     * @return affectNum
     * @throws BussException
     */
    int downBanner(Long bannerId) throws BussException;

    /**
     * 删除banner
     *
     * @param bannerId
     * @return affectNum
     * @throws BussException
     */
    int deleteBanner(Long bannerId) throws BussException;

    /**
     * 后台查询banner列表
     *
     * @param bannerQueryForm
     * @return Page<BannerVO>
     * @throws BussException
     */
    Page<BannerVO> backQuery(BannerQueryForm bannerQueryForm);

    /**
     * app查询banner列表
     *
     * @param position
     * @return List<BannerVO>
     */
    List<BannerVO> query(Integer position);
}
