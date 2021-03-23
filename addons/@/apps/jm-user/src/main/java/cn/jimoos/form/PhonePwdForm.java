package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author :keepcleargas
 * @date :2020-05-12 14:44.
 */
@Data
public class PhonePwdForm extends AbstractUserForm4L {
    @Size(min = 11, max = 11)
    private String phone;
    @NotEmpty
    private String password;
    private String device;
    private int platform;
}
