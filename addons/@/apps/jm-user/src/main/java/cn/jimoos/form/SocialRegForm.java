package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 社交注册
 *
 * @author :keepcleargas
 * @date :2020-11-27 15:04.
 */
@Data
public class SocialRegForm extends AbstractUserForm4L {
    @Size(min = 11, max = 11)
    private String phone;
    private String code;
    private String inviteCode;
    private String socialId;
    private Byte socialType;
    /**
     * 可选
     */
    private String unionId;
    private String nickname;
    private String avatar;
    private String intro;
    private int platform;
    private String device;
}
