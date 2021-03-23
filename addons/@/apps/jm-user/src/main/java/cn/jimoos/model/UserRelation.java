package cn.jimoos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户 分销 关系.
 *
 * @author :keepcleargas
 * @date :2020-12-19 22:01.
 */
@Data
@TableName(value = "t_user_relation")
public class UserRelation {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 最直接的上家id
     */
    @TableField(value = "parent")
    private Long parent;

    /**
     * 二级上家
     */
    @TableField(value = "parent1")
    private Long parent1;

    /**
     * 三级上家
     */
    @TableField(value = "parent2")
    private Long parent2;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "create_at")
    private Long createAt;

    @TableField(value = "update_at")
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public static final String COL_ID = "id";

    public static final String COL_PARENT = "parent";

    public static final String COL_PARENT1 = "parent1";

    public static final String COL_PARENT2 = "parent2";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_REMARK = "remark";

    public static final String COL_CREATE_AT = "create_at";

    public static final String COL_UPDATE_AT = "update_at";

    public static final String COL_DELETED = "deleted";
}
