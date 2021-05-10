package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.service.ProductCollectService;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import cn.jimoos.vo.ProductCollectVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-05-10 09:44.
 */
@RestController
@RequestMapping("/v1/productCollects")
public class ProductCollectApi {
    @Resource
    ProductCollectService productCollectService;

    /**
     * 获取商品详情信息
     *
     * @param form4L 分页表单
     * @return List<ProductCollectVO>
     */
    @GetMapping(value = "/byUserId", produces = "application/json; charset=utf-8")
    public List<ProductCollectVO> getUserProductCollect(@ModelAttribute AbstractUserPageForm4L form4L) throws BussException {
        return productCollectService.getUserCollects(form4L);
    }
}
