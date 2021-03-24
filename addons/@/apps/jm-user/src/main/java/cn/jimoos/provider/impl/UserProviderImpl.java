package cn.jimoos.provider.impl;

import cn.jimoos.converter.UserConverter;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.user.provider.UserProvider;
import cn.jimoos.user.vo.UserVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户接口实现
 *
 * @author :keepcleargas
 * @date :2021-03-23 20:00.
 */
@Component
@Primary
public class UserProviderImpl implements UserProvider {
    @Resource
    UserConverter userConverter;
    @Resource
    UserRepository userRepository;

    @Override
    public UserVO byCode(String code) {
        return userConverter.convert(userRepository.findByCode(code));
    }

    @Override
    public UserVO byId(Long id) {
        return userConverter.convert(userRepository.findById(id));
    }

    @Override
    public UserVO byPhone(String phone) {
        return userConverter.convert(userRepository.findByPhone(phone));
    }

    @Override
    public List<UserVO> byIds(List<Long> ids) {
        return userConverter.convertList(userRepository.findByIds(ids));
    }
}
