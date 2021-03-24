package cn.jimoos.form;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */

@Data
public class BannerForm {

    /**
     * banner标题
     */
    private String title;

    /**
     * 广告图片
     */
    private String imgUrl;

    /**
     * 位置描述
     */
    private String description;

    /**
     * banner位置
     */
    private Integer position;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 路由类型 id
     */
    private Long routeId;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 跳转路径
     */
    private String paths;

    /**
     * 主题颜色
     */
    private String color;


}