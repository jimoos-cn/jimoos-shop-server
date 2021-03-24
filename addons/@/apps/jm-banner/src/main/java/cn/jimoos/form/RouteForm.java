package cn.jimoos.form;


import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */

@Data
public class RouteForm {

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

}