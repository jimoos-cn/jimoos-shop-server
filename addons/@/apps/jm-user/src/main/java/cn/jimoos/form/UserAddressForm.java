package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

/**
 * 地址修改
 *
 * @author :keepcleargas
 * @date :2020-12-08 19:33.
 */
@Data
public class UserAddressForm extends AbstractUserForm4L {
    private String name;

    private String phone;

    private String province;

    private String city;

    private String area;

    private String address;
    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 0 不默认 1默认
     */
    private Boolean defaultIn;

    /**
     * 0 家 1 学校 2公司
     */
    private String tag;
}
