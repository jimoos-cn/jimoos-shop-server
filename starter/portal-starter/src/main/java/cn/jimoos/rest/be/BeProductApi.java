package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.product.*;
import cn.jimoos.service.ProductService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ProductSkuVO;
import cn.jimoos.vo.ProductVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品管理接口
 *
 * @author :keepcleargas
 * @date :2021-04-02 16:08.
 */
@RestController
@RequestMapping("/bAdmin/v1/products")
public class BeProductApi {
    @Resource
    ProductService productService;

    /**
     * 添加完整商品
     * !注意 这里使用的  json 提交数据表单
     *
     * @param form product form
     * @return ProductVO
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public ProductVO addProduct(@RequestBody BeProductForm form) {
        return productService.addFullProduct(form);
    }

    /**
     * 可选分布执行商品添加
     * 第一步 添加商品信息
     * !注意 这里使用的  json 提交数据表单
     *
     * @param form product form#skus 信息为空的
     * @return ProductVO
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    @PostMapping(value = "/info", produces = "application/json; charset=utf-8")
    public ProductVO addProductInfo(@RequestBody BeProductForm form) throws BussException {
        return productService.saveProductInfo(form);
    }


    /**
     * 批量更新 SKU 或则 修改1个 SKu
     * 第二步 添加商品信息
     * !注意 这里使用的  json 提交数据表单
     *
     * @param form product form
     * @return ProductVO
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    @PostMapping(value = "/{productId}/skus", produces = "application/json; charset=utf-8")
    public List<ProductSkuVO> saveSkus(@RequestBody BeProductSkusForm form) throws BussException {
        return productService.saveSkus(form);
    }


    /**
     * 修改商品信息
     *
     * @param form product form#skus 信息为空的
     * @return ProductVO
     * @throws BussException ProductError.PRODUCT_NOT_EXIST
     */
    @PostMapping(value = "/{id}/info", produces = "application/json; charset=utf-8")
    public ProductVO updateProductInfo(@RequestBody BeProductForm form) throws BussException {
        return productService.saveProductInfo(form);
    }

    /**
     * 上架商品
     *
     * @param form #productId
     */
    @PostMapping(value = "/{productId}/up", produces = "application/json; charset=utf-8")
    public void upProduct(@ModelAttribute BeProductStatusForm form) throws BussException {
        productService.up(form);
    }

    /**
     * 下架商品
     *
     * @param form #productId
     */
    @PostMapping(value = "/{productId}/down", produces = "application/json; charset=utf-8")
    public void downProduct(@ModelAttribute BeProductStatusForm form) throws BussException {
        productService.down(form);
    }


    /**
     * 删除商品
     *
     * @param form #productId
     */
    @PostMapping(value = "/{productId}/delete", produces = "application/json; charset=utf-8")
    public void deleteProduct(@ModelAttribute BeProductDeleteForm form) throws BussException {
        productService.delete(form);
    }


    /**
     * 查询商品下的 SKU 列表
     *
     * @param productId product id
     * @return ProductVO
     */
    @GetMapping(value = "/{productId}/skus", produces = "application/json; charset=utf-8")
    public List<ProductSkuVO> skus(@PathVariable Long productId) throws BussException {
        return productService.skus(productId);
    }

    /**
     * 为 Table 查询商品列表
     *
     * @param form product query form for table
     * @return ProductVO
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<ProductVO> query(@ModelAttribute BeProductQueryForm form) {
        return productService.qTable(form);
    }

    /**
     * 获取商品详情信息
     *
     * @param productId product Id
     * @return ProductVO
     */
    @GetMapping(value = "/{productId}", produces = "application/json; charset=utf-8")
    public ProductVO getProduct(@PathVariable("productId") Long productId) throws BussException {
        return productService.getOne(productId);
    }
}
