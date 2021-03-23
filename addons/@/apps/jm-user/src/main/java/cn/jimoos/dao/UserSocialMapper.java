package cn.jimoos.dao;

import cn.jimoos.model.UserSocial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-11-25 23:32.
 */

@Mapper
public interface UserSocialMapper {
    int insert(UserSocial record);

    int batchInsert(@Param("list") List<UserSocial> list);

    /**
     * 根据 社交 UnionId 查询
     *
     * @param socialUnionId unionID
     * @return 社交记录
     */
    UserSocial findOneBySocialUnionId(@Param("socialUnionId") String socialUnionId);

    /**
     * 根据 社交 ID 和 类别查询 社交记录
     *
     * @param socialId 社交 ID
     * @param type     社交类型
     * @return 社交记录
     */
    UserSocial findOneBySocialIdAndType(@Param("socialId") String socialId, @Param("type") Byte type);
}
