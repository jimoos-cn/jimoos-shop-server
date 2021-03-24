package cn.jimoos.error;

import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:42.
 */
public enum RouteError implements IErrorCode {
    ROUTE_NOT_FOUND("route.route_not_found", "路由不存在");

    private String code;
    private String desc;

    RouteError(String code, String desc) {
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
