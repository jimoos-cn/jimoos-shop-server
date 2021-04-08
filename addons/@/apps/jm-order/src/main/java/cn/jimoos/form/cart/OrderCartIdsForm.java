package cn.jimoos.form.cart;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/12/28 21:08
 */
@Data
public class OrderCartIdsForm extends AbstractUserForm4L {
    /**
     * cartIds
     */
    @NotEmpty
    private List<Long> cartIds;
}
