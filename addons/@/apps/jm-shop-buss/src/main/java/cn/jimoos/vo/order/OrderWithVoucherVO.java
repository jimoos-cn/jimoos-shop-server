package cn.jimoos.vo.order;

import cn.jimoos.model.OrderVoucher;
import cn.jimoos.vo.OrderVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SiletFlower
 * @date 2021/9/29 15:00:53
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithVoucherVO extends OrderVO{
    OrderVoucher voucher;
}
