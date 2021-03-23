package cn.jimoos.dao;

import cn.jimoos.user.model.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-11-30 11:43.
 */

@Mapper
public interface UserAddressMapper {
    int updateDeletedByIdAndUserId(@Param("updatedDeleted") Boolean updatedDeleted, @Param("id") Long id, @Param("userId") Long userId);

    int insert(UserAddress record);

    int updateByPrimaryKey(UserAddress record);


    /**
     * 查询地址
     *
     * @param id 地址 ID
     * @return
     */
    UserAddress findOneByIdAndDeletedFalse(@Param("id") Long id);


    /**
     * 获取用户 送货地址
     *
     * @param uid 用户 ID
     * @return 地址列表
     */
    List<UserAddress> selectByUid(@Param("uid") Long uid);

    /**
     * 更新 地址默认状态
     *
     * @param addressId 地址 ID
     * @param status    默认状态
     * @return affectNum
     */
    int updateDefaultStatus(@Param("addressId") Long addressId, @Param("status") boolean status);

    /**
     * 获取 默认地址
     *
     * @param uid 用户 ID
     * @return 地址
     */
    UserAddress selectDefaultAddress(@Param("uid") Long uid);

    /**
     * 设置 原默认地址 为 非默认
     *
     * @param uid              用户 ID
     * @param defaultAddressId 默认地址 ID
     * @return affectNum
     */
    int resetDefaultStatus(@Param("uid") Long uid, @Param("defaultAddressId") Long defaultAddressId);
}
