package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.offline.OfflinePayForm;
import cn.jimoos.model.OrderVoucher;
import cn.jimoos.service.OrderVoucherService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.order.OrderWithVoucherVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/9/29 11:33:15
 * @description
 * 
 * 线下支付相关接口
 */
@RestController
@RequestMapping("/bAdmin/v1/offlinePay")
public class BeOfflinePayApi {

    @Resource
    OrderVoucherService orderVoucherService;

    /**
     * 分页查询审核列表
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public Page<OrderVoucher> getPage(@ModelAttribute OfflinePayForm offlinePayForm) {
        return orderVoucherService.getPage(offlinePayForm);
    }

    /**
     * 详情
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public OrderWithVoucherVO getPage(@PathVariable("id")Long id) throws BussException {
        return orderVoucherService.getDetail(id);
    }

    /**
     * 线下支付商家 审核通过
     *
     */
    @PostMapping(value = "/pass", produces = "application/json; charset=utf-8")
    public void pass(@ModelAttribute OfflinePayForm offlinePayForm) throws BussException {
        orderVoucherService.pass(offlinePayForm);
    }

    /**
     * 线下支付商家 审核不通过
     *
     */
    @PostMapping(value = "/fail", produces = "application/json; charset=utf-8")
    public void fail(@ModelAttribute OfflinePayForm offlinePayForm) throws BussException {
        orderVoucherService.fail(offlinePayForm);
    }
    
}
