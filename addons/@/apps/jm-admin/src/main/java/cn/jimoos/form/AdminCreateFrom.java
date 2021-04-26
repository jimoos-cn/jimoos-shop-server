package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 新建表单
 *
 * @author :keepcleargas
 * @date 2021/04/26 14:46
 */
@Data
public class AdminCreateFrom extends AbstractAdminForm4L {
    @NotNull
    private String account;
    @NotNull
    private String pwd;

}
