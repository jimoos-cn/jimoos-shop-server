package cn.jimoos.vo;

import cn.jimoos.model.CouponRecord;
import cn.jimoos.user.vo.UserVO;
import lombok.Data;

/**
 * 领券记录
 *
 * @author :keepcleargas
 * @date :2021-03-27 21:47.
 */
@Data
public class CouponRecordVO extends CouponRecord {
    private UserVO user;
}
