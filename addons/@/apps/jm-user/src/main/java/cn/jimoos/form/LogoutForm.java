package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2020-11-27 16:31.
 */
@Data
public class LogoutForm extends AbstractUserForm4L {
    private String device;
    private int platform;
}
