package cn.jimoos.form.offline;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/9/29 11:38:04
 * @description
 */
@Data
public class OfflinePayForm extends AbstractAdminPageForm4L {
    
    /**
     * 凭证Id
     */
    private Long id;

    /**
     * 不通过原因
     */
    private String reason;

    /**
     * 订单Id
     */
    private Long orderId;

    /**
     * 状态
     */
    private Byte status;
}
