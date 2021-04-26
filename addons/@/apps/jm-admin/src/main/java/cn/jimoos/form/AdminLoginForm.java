package cn.jimoos.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author :keepcleargas
 * @date 2021/04/26 14:47
 */
@Data
public class AdminLoginForm {
    @NotNull
    private String account;
    @NotNull
    private String pwd;
}
