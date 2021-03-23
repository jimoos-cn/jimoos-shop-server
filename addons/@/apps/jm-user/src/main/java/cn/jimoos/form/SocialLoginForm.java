package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

/**
 * 社交登录
 *
 * @author :keepcleargas
 * @date :2020-11-27 15:04.
 */
@Data
public class SocialLoginForm extends AbstractUserForm4L {
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
