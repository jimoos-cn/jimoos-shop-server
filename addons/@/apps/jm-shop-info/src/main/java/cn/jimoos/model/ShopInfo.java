package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author :keepcleargas
 * @date   :2021-04-16 22:11.
 */

@Data
@NoArgsConstructor
public class ShopInfo {
    private Long id;

    /**
    * 名称
    */
    private String shopName;

    /**
    * 商城简介
    */
    private String shopIntro;

    /**
    * 管理员手机号
    */
    private String shopPhone;

    /**
    * 管理员邮箱
    */
    private String shopEmail;

    /**
    * 商城介绍
    */
    private String shopAbout;

    private Long createAt;

    private Long updateAt;
}