package cn.jimoos.form;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author :keepcleargas
 * @date :2020-04-24 22:41.
 */
@Data
public class ForgotForm {
    @Size(min = 11, max = 11)
    private String phone;
    private String code;
    private String password;
}
