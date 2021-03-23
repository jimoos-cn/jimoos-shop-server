package cn.jimoos.service.impl;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.UserAddressMapper;
import cn.jimoos.form.UserAddressForm;
import cn.jimoos.service.UserAddressService;
import cn.jimoos.user.model.UserAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址 增删改查
 *
 * @author :keepcleargas
 * @date :2020-12-08 19:28.
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    /**
     * The User address mapper.
     */
    @Resource
    UserAddressMapper userAddressMapper;

    @Override
    public UserAddress addAddress(UserAddressForm userAddressForm) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressForm, userAddress);
        long now = System.currentTimeMillis();
        userAddress.setCreateAt(now);
        userAddress.setUpdateAt(now);
        userAddress.setDeleted(false);

        userAddressMapper.insert(userAddress);
        Long addressId = userAddress.getId();
        if (Boolean.TRUE.equals(userAddressForm.getDefaultIn())) {
            //更换 默认地址
            userAddressMapper.resetDefaultStatus(userAddressForm.getUserId(), addressId);
        }
        return userAddress;
    }


    @Override
    public UserAddress modifyAddress(Long addressId, UserAddressForm userAddressForm) throws BussException {
        UserAddress userAddress = userAddressMapper.findOneByIdAndDeletedFalse(addressId);
        if (userAddress == null) {
            throw new BussException(ErrorCodeDefine.RECORD_NOT_EXISTS);
        }
        BeanUtils.copyProperties(userAddressForm, userAddress);
        userAddress.setUpdateAt(System.currentTimeMillis());
        userAddressMapper.updateByPrimaryKey(userAddress);
        if (Boolean.TRUE.equals(userAddressForm.getDefaultIn())) {
            //更换 默认地址
            userAddressMapper.resetDefaultStatus(userAddressForm.getUserId(), addressId);
        }
        return userAddress;
    }


    @Override
    public void deleteAddress(Long addressId, Long uid) {
        userAddressMapper.updateDeletedByIdAndUserId(Boolean.TRUE, addressId, uid);
    }


    @Override
    public void setAddressDefault(Long userId, Long addressId) {
        //设置 默认地址
        userAddressMapper.updateDefaultStatus(addressId, true);
        //重置 原来默认地址
        userAddressMapper.resetDefaultStatus(userId, addressId);
    }

    @Override
    @Nullable
    public UserAddress getDefaultAddress(Long userId) {
        return userAddressMapper.selectDefaultAddress(userId);
    }

    @Override
    @Nullable
    public UserAddress getById(Long addressId) {
        return userAddressMapper.findOneByIdAndDeletedFalse(addressId);
    }


    @Override
    public List<UserAddress> getAddressList(Long userId) {
        return userAddressMapper.selectByUid(userId);
    }
}
