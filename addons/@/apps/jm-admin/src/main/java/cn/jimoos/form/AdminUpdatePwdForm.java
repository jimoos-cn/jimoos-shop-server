package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修改密码
 *
 * @author :keepcleargas
 * @date :2020-05-15 19:27.
 */
@Data
public class AdminUpdatePwdForm extends AbstractAdminForm4L {
    private Long toUpdateAdminId;
    @NotNull
    private String pwd;
}
