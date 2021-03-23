package cn.jimoos.utils.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于后台的 分页查询
 *
 * @author :keepcleargas
 * @date :2021-01-15 10:43.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AbstractAdminPageForm4L extends PageForm {
    private long adminId;
}
