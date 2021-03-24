package cn.jimoos.vo;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */

@Data
public class BannerVO {
    private Long id;

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
     * 发布状态(0下架，1上架)
     */
    private Integer status;

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

    /**
     * 创建时间
     */
    private Long createAt;

}