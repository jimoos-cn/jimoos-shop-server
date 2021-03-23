package cn.jimoos.error;


import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2020-04-24 22:14.
 */
public enum UserError implements IErrorCode {
    SOCIAL_NOT_BIND("user.social_not_bind", "社交手机未绑定"),
    USER_NOT_FOUND("user.user_not_found", "用户找不到了"),
    USER_IS_BAN("user.user_is_ban", "账号被冻结了"),
    WX_LOGIN_ERROR("user.wx_login_error", "微信登录错误"),
    PHONE_IS_BIND("user.phone_is_bind", "手机号已被绑定"),
    PHONE_IS_EXIST("user.phone_is_exist", "手机号已注册"),
    PHONE_NOT_FOUND("user.phone_not_found", "手机号未注册"),
    PWD_NOT_VALID("user.pwd_not_valid", "密码不正确"),
    CODE_NOT_VALID("user.code_not_valid", "验证码不正确"),
    USER_IS_EXIST("user.is_exist","用户已注册");
    private String code;
    private String desc;

    UserError(String code, String desc) {
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
