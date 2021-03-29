package cn.jimoos.form.tag;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @date 2021-03-29 20:52.
 */
@Data
public class BeProductTagCreateForm {
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
