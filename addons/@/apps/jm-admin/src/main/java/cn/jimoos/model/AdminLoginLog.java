package cn.jimoos.model;

import lombok.Data;

/**
 * @author :wangyuhao
 * @date :2020-10-22 11:34.
 */

@Data
public class AdminLoginLog {
    private Long id;

    private Long adminId;

    private String ip;

    /**
     * 请求终端
     */
    private String agent;

    private Long createAt;
}
