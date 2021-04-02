package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :keepcleargas
 * @date :2021-04-02 13:45.
 */

@Data
@NoArgsConstructor
public class UserProductCollection {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 创建时间
     */
    private Long createAt;
}