package cn.jimoos.event.user;

import cn.jimoos.JmEvent;
import lombok.Data;

import java.util.Map;

/**
 * 登录事件
 *
 * @author :keepcleargas
 * @date :2021-03-22 10:54.
 */
@Data
public class LoginEvent extends JmEvent {
    public LoginEvent(long userId) {
        this.userId = userId;
    }

    public LoginEvent(long userId, Map extra) {
        this.userId = userId;
        this.extra = extra;
    }

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 注册时间
     */
    private long created;

    private Map extra;
}
