package cn.jimoos.form;

import cn.jimoos.utils.form.PageForm;
import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */

@Data
public class BannerQueryForm extends PageForm {

    /**
     * banner标题
     */
    private String title;

    /**
     * banner位置
     */
    private Integer position;

    /**
     * 发布状态(0下架，1上架)
     */
    private Integer status;

}