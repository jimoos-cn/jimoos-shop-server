package cn.jimoos.product.provider;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.product.model.ProductItem;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 商品信息获取
 *
 * @author :keepcleargas
 * @date :2021-04-07 16:26.
 */
public interface IProductProvider {
    /**
     * 获取 商品明细 可能返回空 上层需进行校验
     *
     * @param uniqueId uniqueId 惟一键，比如商品模块一般为 sku.id，VIP 模块可能就是 vipItem.id
     * @return ProductItem
     */
    @Nullable
    ProductItem byId(Long uniqueId) throws BussException;

    /**
     * 占用库存
     *
     * @param uniqueId uniqueId
     * @param number   number
     * @throws BussException BussException
     */
    void occupyStock(Long uniqueId, int number) throws BussException;

    /**
     * 释放库存
     *
     * @param uniqueId uniqueId
     * @param number   number
     * @throws BussException BussException
     */
    void unOccupyStock(Long uniqueId, int number) throws BussException;

    /**
     * 批量获取 商品明细 返回数量与传入 uniqueIds 数量可能不一致，上层需进行校验
     *
     * @param uniqueIds uniqueIds 惟一键，比如商品模块一般为 sku.id，VIP 模块可能就是 vipItem.id
     * @return List<ProductItem>
     */
    List<ProductItem> byIds(List<Long> uniqueIds);
}
