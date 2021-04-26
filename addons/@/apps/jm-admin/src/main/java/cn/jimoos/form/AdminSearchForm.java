package cn.jimoos.form;

import cn.jimoos.utils.form.PageForm;
import lombok.Data;

/**
 * @author keepcleargas
 * @date 2021/04/26 14:46
 * 管理员查询
 */
@Data
public class AdminSearchForm extends PageForm {

    /**
     * 管理员名字关键字
     */
    private String name;
}
