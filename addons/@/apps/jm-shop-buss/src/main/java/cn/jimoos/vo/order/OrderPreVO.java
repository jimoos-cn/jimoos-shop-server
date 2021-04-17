package cn.jimoos.vo.order;

import cn.jimoos.vo.AbstractOrderPre;
import lombok.Data;

/**
 * 生成订单预览
 *
 * @author :keepcleargas
 * @date :2021-04-13 11:11.
 */
@Data
public class OrderPreVO<T> extends AbstractOrderPre {
    public OrderPreVO() {
    }

    public OrderPreVO(T receipt) {
        this.receipt = receipt;
    }

    T receipt;
}
