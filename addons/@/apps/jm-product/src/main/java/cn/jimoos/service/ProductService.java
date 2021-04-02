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
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    ProductVO saveProductInfo(BeProductForm beProductForm) throws BussException;

    /**
     * 新建 或 批量更新 SKU
     *
     * @param beProductSkusForm skus form
     * @return List<ProductSkuVO>
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    List<ProductSkuVO> saveSkus(BeProductSkusForm beProductSkusForm) throws BussException;

    /**
     * 获取 商品详情
     *
     * @param productId product Id
     * @return ProductVO
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    ProductVO getOne(Long productId) throws BussException;

    /**
     * 查询商品列表, 用于后台 table 管理查询
     *
     * @param queryForm query form
     * @return Page<ProductVO>
     */
    Page<ProductVO> qTable(BeProductQueryForm queryForm);

    /**
     * 客户端 查询商品列表
     *
     * @param searchForm search form
     * @return List<ProductVO>
     */
    List<ProductVO> search(ProductSearchForm searchForm);

    /**
     * 获取 SKU 列表
     *
     * @param productId product Id
     * @return List<ProductSkuVO>
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    List<ProductSkuVO> skus(Long productId) throws BussException;

    /**
     * 上架
     *
     * @param statusForm status
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    void up(BeProductStatusForm statusForm) throws BussException;

    /**
     * 下架
     *
     * @param statusForm status
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    void down(BeProductStatusForm statusForm) throws BussException;

    /**
     * 删除 商品
     *
     * @param beProductDeleteForm productId
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    void delete(BeProductDeleteForm beProductDeleteForm) throws BussException;
}
