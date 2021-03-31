package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.product.*;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductSkuVO;
import cn.jimoos.vo.ProductVO;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 21:03.
 */
public interface ProductService {
    /**
     * 完整添加商品
     *
     * @param beProductForm 商品表单
     * @return ProductVO
     */
    ProductVO addFullProduct(BeProductForm beProductForm);

    /**
     * 新建 或 更新 商品信息
     *
     * @param beProductForm product form 不包含 SKU
     * @return ProductVo
     */
    ProductVO saveProductInfo(BeProductForm beProductForm);

    /**
     * 新建 或 批量更新 SKU
     *
     * @param beProductSkusForm skus form
     * @return List<ProductSkuVO>
     */
    List<ProductSkuVO> saveSkus(BeProductSkusForm beProductSkusForm) throws BussException;

    /**
     * 获取 商品详情
     *
     * @param productId product Id
     * @return ProductVO
     */
    ProductVO getOne(Long productId);

    /**
     * 查询商品列表
     *
     * @param queryForm query form
     * @return Page<ProductVO>
     */
    Page<ProductVO> query(BeProductQueryForm queryForm);

    /**
     * 获取 SKU 列表
     *
     * @param productId product Id
     * @return List<ProductSkuVO>
     */
    List<ProductSkuVO> skus(Long productId);

    /**
     * 更新商品 上下架状态
     *
     * @param statusForm status
     * @return affectNum
     */
    int updateProductStatus(BeProductStatusForm statusForm);

    /**
     * 删除 商品
     *
     * @param beProductDeleteForm productId
     * @return affectNum
     */
    int delete(BeProductDeleteForm beProductDeleteForm);
}
