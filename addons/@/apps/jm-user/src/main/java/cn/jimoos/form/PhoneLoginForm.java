package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author :keepcleargas
 * @date :2020-04-24 18:26.
 */
@Data
public class PhoneLoginForm extends AbstractUserForm4L {
    @Size(min = 11, max = 11)
    private String phone;
    private String code;
    private String inviteCode;
    private String device;
    private int platform;
}
