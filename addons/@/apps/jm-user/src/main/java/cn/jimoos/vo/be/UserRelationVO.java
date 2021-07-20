package cn.jimoos.vo.be;

import cn.jimoos.model.UserRelation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author SiletFlower
 * @date 2021/7/15 17:42:35
 * @description 用户分销关系
 */
@Data
public class UserRelationVO extends UserRelation {

    /**
     * 转换
     * @param userRelation
     * @return UserRelationVO
     */
    public static UserRelationVO toVO(UserRelation userRelation) {
        UserRelationVO userRelationVO = new UserRelationVO();
        BeanUtils.copyProperties(userRelation, userRelationVO);
        return userRelationVO;
    }

}
