package cn.jimoos.impl;

import cn.jimoos.JmEvent;
import cn.jimoos.JmEventPublisher;
import cn.jimoos.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 事件 发布器
 *
 * @author :keepcleargas
 * @date :2021-03-22 14:38.
 */
@Component
@Slf4j
public class JmSpringEventPublisher implements JmEventPublisher {
    final
    ApplicationEventPublisher applicationEventPublisher;

    public JmSpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void publish(JmEvent event) {
        log.debug("publish event:" + JsonMapper.INSTANCE.toJson(event));
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void asyncPublish(JmEvent event) {
        log.debug("publish sync event:" + JsonMapper.INSTANCE.toJson(event));
        applicationEventPublisher.publishEvent(event);
    }
}
