package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.UserAddressForm;
import cn.jimoos.service.UserAddressService;
import cn.jimoos.user.model.UserAddress;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-12-08 19:45.
 */
@RestController
@RequestMapping("/v1/users")
public class UserAddressApi {
    @Resource
    UserAddressService userAddressService;

    /**
     * 获取我的地址列表
     *
     * @param userId 用户 ID
     * @return 用户地址列表
     */
    @GetMapping(value = "/{userId}/addresses", produces = "application/json; charset=utf-8")
    public List<UserAddress> getAddress(@PathVariable("userId") long userId) {
        return userAddressService.getAddressList(userId);
    }

    /**
     * 获取 默认地址
     *
     * @param userId 用户 ID
     * @return 用户地址
     */
    @GetMapping(value = "/{userId}/addresses/default", produces = "application/json; charset=utf-8")
    public UserAddress getDefaultAddress(@PathVariable("userId") long userId) {
        return userAddressService.getDefaultAddress(userId);
    }

    /**
     * 设置 默认地址
     *
     * @param userId 用户 ID
     */
    @PostMapping(value = "/{userId}/addresses/default", produces = "application/json; charset=utf-8")
    public void setDefaultAddress(@PathVariable("userId") long userId, @RequestParam("addressId") long addressId) {
        userAddressService.setAddressDefault(userId, addressId);
    }

    /**
     * 添加地址
     *
     * @return 用户地址
     */
    @PostMapping(value = "/{userId}/addresses", produces = "application/json; charset=utf-8")
    public UserAddress addAddress(@ModelAttribute UserAddressForm userAddressForm) {
        return userAddressService.addAddress(userAddressForm);
    }

    /**
     * 修改
     *
     * @return 用户修改后的地址
     */
    @PostMapping(value = "/{userId}/addresses/{addressId}", produces = "application/json; charset=utf-8")
    public UserAddress getDefaultAddress(@PathVariable("addressId") long addressId,
                                         @ModelAttribute UserAddressForm userAddressForm) throws BussException {
        return userAddressService.modifyAddress(addressId, userAddressForm);
    }

    /**
     * 删除地址
     *
     * @param userId    用户 ID
     * @param addressId 地址 ID
     */
    @DeleteMapping(value = "/{userId}/addresses/{addressId}", produces = "application/json; charset=utf-8")
    public void deleteAddress(@PathVariable("userId") long userId, @PathVariable("addressId") long addressId) {
        userAddressService.deleteAddress(addressId, userId);
    }
}
