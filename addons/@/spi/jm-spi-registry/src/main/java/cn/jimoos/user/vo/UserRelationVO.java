package cn.jimoos.user.vo;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-03-23 11:22.
 */
@Data
public class UserRelationVO {
    /**
     * 最直接的上家id
     */
    private Long parent;

    /**
     * 二级上家
     */
    private Long parent1;

    /**
     * 三级上家
     */
    private Long parent2;

    /**
     * 用户ID
     */
    private Long userId;

    private Long createAt;
}
