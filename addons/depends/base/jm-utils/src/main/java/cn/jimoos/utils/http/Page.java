package cn.jimoos.utils.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @author qisheng.chen
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T> {
    private Long total;
    private List<T> list;

    /**
     * 手动存入的Page分页和数据
     */
    public static <T> Page<T> create(Long total, List<T> list) {
        Page<T> page = new Page<>();
        page.setTotal(total);
        page.setList(list);
        return page;
    }

    public static <T> Page<T> empty() {
        Page<T> page = new Page<>();
        page.setTotal(0L);
        page.setList(new ArrayList<>());
        return page;
    }
}
