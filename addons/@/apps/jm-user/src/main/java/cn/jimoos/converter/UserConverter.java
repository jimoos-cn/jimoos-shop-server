package cn.jimoos.converter;

import cn.jimoos.entity.UserEntity;
import cn.jimoos.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 VO 转换器
 *
 * @author :keepcleargas
 * @date :2020-04-24 20:03.
 */
@Component
public class UserConverter {
    public UserVO convert(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserVO userVo = new UserVO();
        BeanUtils.copyProperties(userEntity, userVo);
        return userVo;
    }

    public List<UserVO> convertList(List<UserEntity> userEntityList) {
        if (!CollectionUtils.isEmpty(userEntityList)) {
            return userEntityList.stream().map(
                    userEntity -> convert(userEntity)
            ).collect(Collectors.toList());
        } else {
            return new ArrayList();
        }
    }
}
