package cn.jimoos.provider.impl;

import cn.jimoos.service.UserAddressService;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.user.provider.AddressProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户地址接口实现
 *
 * @author :keepcleargas
 * @date :2021-03-21 16:03.
 */
@Component
public class UserAddressImpl implements AddressProvider {
    @Resource
    UserAddressService userAddressService;

    @Override
    public UserAddress byId(Long id) {
        return userAddressService.getById(id);
    }
}
