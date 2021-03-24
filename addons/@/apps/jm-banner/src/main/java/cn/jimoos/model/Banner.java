package cn.jimoos.model;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */

@Data
public class Banner {
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

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}