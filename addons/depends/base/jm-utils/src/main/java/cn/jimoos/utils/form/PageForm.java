package cn.jimoos.utils.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页表单
 *
 * @author chenqisheng
 * @date :15/3/17.
 */
public class PageForm {
    @NotNull
    @Min(0)
    protected int offset = 0;
    @NotNull
    @Min(1)
    protected int limit = 20;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
