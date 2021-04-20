package cn.jimoos.error;

import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:56.
 */
public enum PayError implements IErrorCode {
    PAY_ERROR("payment.pay_error", "生成支付失败，请重试"),
    PAY_TYPE_NOT_SUPPORT("payment.pay_type_not_support", "当前支付方式不支持"),
    PAY_NOT_EXIST("payment.not_exist","支付信息不存在！")
    ;

    private String code;
    private String desc;

    PayError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
