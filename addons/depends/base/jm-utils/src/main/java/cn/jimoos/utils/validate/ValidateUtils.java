package cn.jimoos.utils.validate;

import javax.validation.*;
import java.util.Set;

/**
 * @author :keepcleargas
 * @date :2021-04-07 13:19.
 */
public class ValidateUtils {
    /**
     * 线程安全的，直接构建也可以，这里使用静态代码块一样的效果
     */
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(t);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
