package cn.jimoos.dao;

import cn.jimoos.model.UserRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * The interface User relation mapper.
 *
 * @author :keepcleargas
 * @date :2020-12-19 22:01.
 */
@Mapper
public interface UserRelationMapper extends BaseMapper<UserRelation> {

    /**
     * 获取用户的上下级关系
     *
     * @param userId the user id
     * @return user relation
     */
    UserRelation findOneByUserId(@Param("userId") Long userId);

    /**
     * 获取一级下家
     *
     * @param parent the parent
     * @return the list
     */
    List<UserRelation> findByParent(@Param("parent") Long parent);

    /**
     * 根据用户ID 获取一级下家数量
     *
     * @param parent the parent
     * @return the integer
     */
    Integer countByParent(@Param("parent") Long parent);


    /**
     * Find by parent 1 and parent in list.
     *
     * @param parent1          the parent 1
     * @param parentCollection the parent collection
     * @return the list
     */
    List<UserRelation> findByParent1AndParentIn(@Param("parent1") Long parent1, @Param("parentCollection") Collection<Long> parentCollection);

    /**
     * Find by parent 1 list.
     *
     * @param parent1 the parent 1
     * @return the list
     */
    List<UserRelation> findByParent1(@Param("parent1") Long parent1);
}
