package cn.jimoos.form.cart;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/12/28 21:08
 */
@Data
public class OrderCartIdForm {
    private Long uid;
    /**
     * cartIds
     */
    @NotEmpty
    private List<Long> cartIds;
}
