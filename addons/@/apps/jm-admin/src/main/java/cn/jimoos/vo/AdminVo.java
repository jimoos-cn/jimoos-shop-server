package cn.jimoos.vo;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-26 20:29.
 */
@Data
public class AdminVo {
    private Long id;
    private String username;
    private String token;
    private Long createAt;
    private Integer ban;
}
