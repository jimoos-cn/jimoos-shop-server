package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author :keepcleargas
 * @date   :2021-04-29 14:17.
 */

@Data
@NoArgsConstructor
public class Collection {
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 子标题
    */
    private String subTitle;

    /**
    * 推荐 0 未推荐 1 推荐
    */
    private Boolean recommend;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 上下架状态
    */
    private Boolean status;

    /**
    * 类别
    */
    private Integer type;

    private Long createAt;

    private Long updateAt;

    private Boolean deleted;
}