package cn.jimoos.utils.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author :keepcleargas
 * @date :2021-01-13 14:01.
 */
@Data
public class AbstractUserPageForm4L implements UserForm4L {
    private long userId;

    @NotNull
    @Min(0)
    protected int offset = 0;
    @NotNull
    @Min(1)
    protected int limit = 20;
}
