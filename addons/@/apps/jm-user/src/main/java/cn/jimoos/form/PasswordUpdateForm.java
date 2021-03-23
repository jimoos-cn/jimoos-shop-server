package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author :keepcleargas
 * @date :2021-01-14 10:36.
 */
@Data
public class PasswordUpdateForm extends AbstractUserForm4L {
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    private String code;
    @Size(min = 11, max = 11)
    private String phone;
}
