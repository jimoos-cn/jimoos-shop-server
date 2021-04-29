package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author :keepcleargas
 * @date   :2021-04-29 14:17.
 */

@Data
@NoArgsConstructor
public class RCollectionProduct {
    private Long id;

    /**
    * 目标商品 ID
    */
    private Long productId;

    /**
    * 集合 ID
    */
    private Long collectionId;

    private Integer sort;

    private Long createAt;
}