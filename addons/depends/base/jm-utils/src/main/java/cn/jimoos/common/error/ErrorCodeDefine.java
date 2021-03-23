package cn.jimoos.common.error;

/**
 * @author chenqisheng
 * @date :15/3/30.
 */
public enum ErrorCodeDefine implements IErrorCode {
    //通用错误
    DEFAULT_ERROR("base.default", "默认错误"),
    RECORD_NOT_EXISTS("base.not_found", "记录不存在"),
    FORM_PARAMS_NOT_VALID("base.form_params_not_valid", "参数不合法"),
    REQUEST_PARAMS_NOT_VALID("base.request_params_or_body_not_valid", "参数不合法"),
    MISSING_REQUEST_PARAM_ERROR("base.missing_request_param_error", "参数缺失"),
    UNAUTHORIZED("base.unauthorized", "权限不足");

    private String code;
    private String desc;

    ErrorCodeDefine(String code, String desc) {
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
