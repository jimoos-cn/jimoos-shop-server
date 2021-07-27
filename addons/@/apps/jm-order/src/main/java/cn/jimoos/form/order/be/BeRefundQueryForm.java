package cn.jimoos.form.order.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/7/27 14:24:58
 * @description
 */
@Data
public class BeRefundQueryForm extends AbstractAdminPageForm4L {

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 开始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 表单转为hashmap
     */
    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", this.orderNum);
        map.put("start", this.start);
        map.put("end", this.end);
        map.put("offset", this.offset);
        map.put("limit", this.limit);
        return map;
    }
}
