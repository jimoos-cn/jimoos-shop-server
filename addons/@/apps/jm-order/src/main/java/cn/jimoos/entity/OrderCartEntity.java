package cn.jimoos.entity;

import cn.jimoos.form.cart.OrderCartForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.repository.OrderCartRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author keepcleargas
 * @date 2021-04-07 12:59.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCartEntity extends OrderCart {
    private OrderCartRepository orderCartRepository;
    private Boolean deleted;

    public OrderCartEntity(OrderCartRepository orderCartRepository) {
        this.orderCartRepository = orderCartRepository;
    }

    public void update(OrderCartForm form) {
        Boolean checked = form.getChecked();
        this.setChecked(checked);
        this.setNum(form.getNum());
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void check() {
        this.setChecked(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void uncheck() {
        this.setChecked(false);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void delete() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
