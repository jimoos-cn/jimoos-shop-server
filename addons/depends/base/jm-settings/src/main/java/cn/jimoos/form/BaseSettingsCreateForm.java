package cn.jimoos.form;

import lombok.Data;

/**
 * 类BaseSettings对象创建Form
 *
 * @Author toolkit
 * @Date 2018年11月14日
 */
@Data
public class BaseSettingsCreateForm {
    /**
     * id
     */
    private int id;
    /**
     * 关键词, PAY.ALI PAY.MP
     */
    private String keyword;
    /**
     * 配置内容
     */
    private String content;
}
