package cn.jimoos.error;

import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-04-26 20:35.
 */
public enum AdminError implements IErrorCode {
    ACCOUNT_EXIST("admin.account_exist", "账号已存在"),
    ADMIN_NOT_EXIST("admin.admin_not_exist", "管理员不存在"),
    PWD_NOT_VALID("admin.pwd_not_valid", "密码不正确"),
    ADMIN_IS_BAN("admin.admin_is_ban", "禁止登录"),
    ADMIN_NOT_LOGIN("admin.admin_not_login", "未登录/登录失效");
    private String code;
    private String desc;

    AdminError(String code, String desc) {
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
