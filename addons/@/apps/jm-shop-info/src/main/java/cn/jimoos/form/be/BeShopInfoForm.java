package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeShopInfoForm extends AbstractAdminForm4L {
    private String shopName;
    /**
     * 详细介绍 富文本
     */
    private String shopAbout;
    /**
     * 商城简介
     */
    private String shopIntro;

    private String shopPhone;

    private String shopEmail;
}
