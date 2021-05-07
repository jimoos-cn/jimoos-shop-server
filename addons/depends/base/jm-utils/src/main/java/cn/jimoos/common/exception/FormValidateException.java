package cn.jimoos.common.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * 验证失败的异常定义
 *
 * @author keepcleargas
 */
public class FormValidateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Set<ConstraintViolation<?>> violations;

    public FormValidateException() {
        super("The Service form was validated failure.");
    }

    public FormValidateException(Set<ConstraintViolation<?>> violations) {
        this();
        this.violations = violations;
    }

    public Set<ConstraintViolation<?>> getCurrentViolations() {
        return this.violations;
    }
}
