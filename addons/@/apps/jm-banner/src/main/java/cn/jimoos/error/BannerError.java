package cn.jimoos.error;

import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:42.
 */
public enum BannerError implements IErrorCode {
    BANNER_NOT_FOUND("banner.banner_not_found", "banner找不到了");

    private String code;
    private String desc;

    BannerError(String code, String desc) {
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
