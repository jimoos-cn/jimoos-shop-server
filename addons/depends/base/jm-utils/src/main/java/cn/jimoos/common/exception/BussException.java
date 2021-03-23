package cn.jimoos.common.exception;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.error.IErrorCode;
import org.springframework.http.HttpStatus;

/**
 * 业务异常编码
 *
 * @author keepcleargas
 * @date 15/3/11
 */

public class BussException extends Exception {
    private HttpStatus httpStatus;
    private String code;

    public BussException() {
    }

    public BussException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = ErrorCodeDefine.DEFAULT_ERROR.getCode();
    }

    public BussException(String code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BussException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = ErrorCodeDefine.DEFAULT_ERROR.getCode();
    }

    public BussException(IErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
