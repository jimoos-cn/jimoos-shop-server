package cn.jimoos.service;


import cn.jimoos.model.UserProductCollection;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import cn.jimoos.vo.ProductCollectVO;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-02 13:47.
 */
public interface ProductCollectService {
    /**
     * 收藏
     *
     * @param userId    user Id
     * @param productId product Id
     */
    void collect(Long userId, Long productId);

    /**
     * 取消收藏
     *
     * @param userId    user Id
     * @param productId product Id
     */
    void unCollect(Long userId, Long productId);

    /**
     * 查询用户收藏的商品列表
     *
     * @param abstractUserPageForm4L 用户分页表单
     * @return List<ProductCollectVO>
     */
    List<ProductCollectVO> getUserCollects(AbstractUserPageForm4L abstractUserPageForm4L);

    /**
     * 获取用户对某商品的收藏
     *
     * @param userId    user Id
     * @param productId product Id
     * @return UserProductCollection
     */
    UserProductCollection findByUserIdAndProductId(Long userId, Long productId);

    /**
     * 获取用户对商品列表的收藏记录
     *
     * @param userId     user Id
     * @param productIds product Ids
     * @return List<UserProductCollection>
     */
    List<UserProductCollection> findByUserIdAndProductIdIn(Long userId, Collection<Long> productIds);
}
