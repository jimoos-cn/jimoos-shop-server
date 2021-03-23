package cn.jimoos.handler;

import cn.jimoos.dao.UserRelationMapper;
import cn.jimoos.entity.UserEntity;
import cn.jimoos.event.user.RegEvent;
import cn.jimoos.model.UserRelation;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 处理 注册事件
 *
 * @author :keepcleargas
 * @date :2020-12-08 19:12.
 */
@Component
@Slf4j
public class UserRelationHandler {
    @Resource
    UserRelationMapper userRelationMapper;
    @Resource
    UserRepository userRepository;

    @EventListener
    public void handleRegEvent(RegEvent regEvent) {
        log.info("Reg Event Handle:" + JsonMapper.INSTANCE.toJson(regEvent));

        if (!StringUtils.isEmpty(regEvent.getInviteCode())) {
            UserEntity invitor = userRepository.findByCode(regEvent.getInviteCode());

            if (invitor != null) {
                UserRelation userRelation = userRelationMapper.findOneByUserId(invitor.getId());

                UserRelation newRelation = new UserRelation();
                newRelation.setParent(invitor.getId());
                newRelationShip(regEvent, userRelation, newRelation);
            }
        }
    }

    private void newRelationShip(RegEvent regEvent, UserRelation relationship, UserRelation newRelation) {
        if (relationship != null) {
            newRelation.setParent1(relationship.getParent());
            newRelation.setParent2(relationship.getParent1());
        }
        newRelation.setUserId(regEvent.getUserId());
        newRelation.setCreateAt(System.currentTimeMillis());
        newRelation.setDeleted(false);

        userRelationMapper.insert(newRelation);
    }
}
