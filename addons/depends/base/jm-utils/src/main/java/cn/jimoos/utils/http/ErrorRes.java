package cn.jimoos.utils.http;

import lombok.Data;

/**
 * 错误返回结果
 *
 * @author :keepcleargas
 * @date :2018-12-05 12:02.
 */
@Data
public class ErrorRes {
    private String code;
    private String message;

    public ErrorRes() {
    }

    public ErrorRes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
