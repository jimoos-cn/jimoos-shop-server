package cn.jimoos.dto;

import cn.jimoos.model.ProductTag;
import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-01 21:58.
 */
@Data
public class ProductTagDto extends ProductTag {
    private Long productId;
}
