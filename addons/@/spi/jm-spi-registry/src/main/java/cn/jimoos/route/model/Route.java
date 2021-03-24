package cn.jimoos.route.model;


import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */
@Data
public class Route {
    private Long id;

    /**
    * 描述
    */
    private String description;

    /**
    * 路由跳转类型
    */
    private Integer type;

    /**
    * 路由跳转路径
    */
    private String route;

    /**
    * 创建时间
    */
    private Long createAt;

    /**
    * 更新时间
    */
    private Long updateAt;

    /**
    * 0 未删除 1 已删除
    */
    private Boolean deleted;
}