package cn.jimoos.model;

import lombok.Data;

/**
 * @author :wangyuhao
 * @date :2020-10-22 11:40.
 */

@Data
public class AdminToken {
    private Long id;

    private Long adminId;

    private String token;

    private String ip;

    private Long expired;

    private Long createAt;
}
