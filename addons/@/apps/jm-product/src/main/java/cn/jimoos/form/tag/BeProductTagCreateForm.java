package cn.jimoos.form.tag;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @date 2021-03-29 20:52.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductTagCreateForm extends AbstractAdminForm4L {
    /**
     * 标签名称
     */
    @NotNull
    private String name;

    /**
     * 标签类型 0 普通标签
     */
    private Byte type = 0;

    /**
     * 主题颜色
     */
    @NotNull
    private String color;
}
