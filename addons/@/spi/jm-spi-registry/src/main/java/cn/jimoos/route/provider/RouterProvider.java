package cn.jimoos.route.provider;


import cn.jimoos.route.model.Route;

import java.util.Collection;
import java.util.List;

/**
 * 路径 请求
 *
 * @author :keepcleargas
 * @date :2021-01-25 14:54.
 */
public interface RouterProvider {
    Route byId(Long id);

    List<Route> byIds(Collection<Long> ids);
}
