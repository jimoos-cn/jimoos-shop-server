package cn.jimoos.form;

import lombok.Data;

/**
 * 手机注册
 *
 * @author :keepcleargas
 * @date :2020-11-26 22:17.
 */
@Data
public class PhoneRegForm {
    private String phone;
    private String password;
    private String code;

    private String inviteCode;
    private String device = "";
    private int platform = 0;
}
