package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

/**
 * 更新信息
 *
 * @author :keepcleargas
 * @date :2020-11-26 22:42.
 */
@Data
public class ProfileForm extends AbstractUserForm4L {
    private String nickname;
    private String avatar;
    private Byte gender = -1;
    private Long birthday = -1L;

    private String province;
    private String city;
    private String area;
}
