package cn.jimoos.user.provider;


import cn.jimoos.user.model.UserAddress;

/**
 * 地址提供接口
 *
 * @author :keepcleargas
 * @date :2021-03-21 16:02.
 */
public interface AddressProvider {
    /**
     * 根据 ID 查找地址
     *
     * @param id
     * @return
     */
    UserAddress byId(Long id);
}
