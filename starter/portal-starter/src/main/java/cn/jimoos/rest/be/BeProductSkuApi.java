package cn.jimoos.rest.be;

import cn.jimoos.form.sku.BeProductSkuUpdateForm;
import cn.jimoos.service.ProductSkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/20 11:39:57
 * @description
 */
@RestController
@RequestMapping("/bAdmin/v1/product/sku")
public class BeProductSkuApi {

    @Resource
    ProductSkuService productSkuService;

    @PostMapping(value = "/{skuId}/delete", produces = "application/json; charset=utf-8")
    public void deleteProductSku(@PathVariable("skuId") Long skuId) {
        productSkuService.deleteProductSku(skuId);
    }


    /**
     * 更新某商品的sku列表 ContentType为json
     *
     * @param beProductSkuUpdateForms 更新sku表单
     */
    @PostMapping(value = "/update", produces = "application/json; charset=utf-8")
    public void updateProductSku(@RequestBody List<BeProductSkuUpdateForm> beProductSkuUpdateForms) {
        productSkuService.updateProductSku(beProductSkuUpdateForms);
    }
}
