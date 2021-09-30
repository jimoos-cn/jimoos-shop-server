package cn.jimoos.service;


import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.offline.OfflinePayForm;
import cn.jimoos.model.OrderVoucher;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.order.OrderWithVoucherVO;

public interface OrderVoucherService {

    /**
     * 上传凭证并对order的extra内容修改
     *
     * @param record
     * @return
     */
    void save(OrderVoucher record);

    /**
     * 凭证通过
     *
     * @param offlinePayForm
     */
    void pass(OfflinePayForm offlinePayForm) throws BussException;

    /**
     * 凭证不通过
     *
     * @param offlinePayForm
     */
    void fail(OfflinePayForm offlinePayForm) throws BussException;

    /**
     * 根据订单Id获取最新一条线下凭证记录
     */
    OrderVoucher getOne(Long orderId);

    /**
     * 获取后台分页 凭证记录
     *
     * @param offlinePayForm
     * @return
     */
    Page<OrderVoucher> getPage(OfflinePayForm offlinePayForm);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    OrderWithVoucherVO getDetail(Long id) throws BussException;
}


