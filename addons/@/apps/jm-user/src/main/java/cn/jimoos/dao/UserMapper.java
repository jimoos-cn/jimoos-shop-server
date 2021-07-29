package cn.jimoos.dao;

import cn.jimoos.user.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2020-11-26 21:54.
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);

    int updateById(@Param("updated")User updated,@Param("id")Long id);



    /**
     * 根据用户手机号 获取用户
     *
     * @param phone 手机号
     * @return 用户
     */
    User findOneByPhone(@Param("phone") String phone);

    /**
     * 根据邀请码 获取用户
     *
     * @param inviteCode 邀请码
     * @return User 用户
     */
    User findOneByCode(@Param("inviteCode") String inviteCode);

    /**
     * 统计所有的用户数
     *
     * @return 用户总数
     */
    int countTotalUser();

    /**
     * 批量查询用户
     *
     * @param idCollection 用户 ID 集合
     * @return 用户列表
     */
    List<User> findByIdIn(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 查询 User 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${nickname},${role},${phone} 的 倒序分页查询
     * @return List<User>
     */
    List<User> queryTable(Map qm);

    /**
     * 查询 User 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的${nickname},${role},${phone} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map qm);
}
