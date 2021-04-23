package cn.jimoos.model;

import lombok.Data;

/**
 * @author :wangyuhao
 * @date :2020-10-22 11:33
 */

@Data
public class Admin {
    private Long id;

    private String username;

    private String encryptedPassword;

    private String salt;

    private Integer ban;

    private Long createAt;

    /**
     * 0表示不删除,1表示删除
     */
    private Integer deleted;
}
