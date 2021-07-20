package cn.jimoos.vo.be;

import cn.jimoos.user.model.UserAddress;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author SiletFlower
 * @date 2021/7/15 15:05:00
 * @description 后台查询的用户填写的收货地址
 */
@Data
public class UserAddressVO extends UserAddress {

    /**
     *
     * @param userAddress 用户地址信息
     */
    public static UserAddressVO toVO(UserAddress userAddress) {
        UserAddressVO userAddressVO = new UserAddressVO();
        BeanUtils.copyProperties(userAddress, userAddressVO);
        return userAddressVO;
    }
}
