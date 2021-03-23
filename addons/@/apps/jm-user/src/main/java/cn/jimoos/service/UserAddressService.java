package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.UserAddressForm;
import cn.jimoos.user.model.UserAddress;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-23 18:05.
 */
public interface UserAddressService {
    /**
     * 添加地址
     *
     * @param userAddressForm the user address form
     * @return the user address
     */
    UserAddress addAddress(UserAddressForm userAddressForm);

    /**
     * 修改
     *
     * @param addressId       the address id
     * @param userAddressForm the user address form
     * @return the user address
     */
    UserAddress modifyAddress(Long addressId, UserAddressForm userAddressForm) throws BussException;

    /**
     * 删除地址
     *
     * @param addressId the address id
     */
    void deleteAddress(Long addressId, Long uid);

    /**
     * 设置 默认
     *
     * @param userId    the user id
     * @param addressId the address id
     */
    void setAddressDefault(Long userId, Long addressId);

    /**
     * 返回 默认地址
     *
     * @param userId the user id
     * @return default address
     */
    @Nullable
    UserAddress getDefaultAddress(Long userId);

    /**
     * 根据 ID 获取 address
     *
     * @param addressId the address id
     * @return the by id
     */
    @Nullable
    UserAddress getById(Long addressId);

    /**
     * 获取用户地址列表
     *
     * @param userId the user id
     * @return address list
     */
    List<UserAddress> getAddressList(Long userId);
}
