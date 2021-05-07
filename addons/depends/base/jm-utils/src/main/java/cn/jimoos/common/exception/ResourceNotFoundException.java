package cn.jimoos.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: QeeMo-Dev-03
 * Date: 13-1-25
 * Time: 上午11:00
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 8157853138132137706L;
}
