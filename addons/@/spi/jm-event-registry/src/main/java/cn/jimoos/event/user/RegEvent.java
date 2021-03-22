package cn.jimoos.event.user;

import cn.jimoos.JmEvent;
import lombok.Data;

/**
 * 用户注册事件
 *
 * @author :keepcleargas
 * @date :2021-03-22 16:59.
 */
@Data
public class RegEvent extends JmEvent {
    public RegEvent() {
    }

    public RegEvent(long userId, String inviteCode, long created) {
        this.userId = userId;
        this.inviteCode = inviteCode;
        this.created = created;
    }

    public RegEvent(long userId, String inviteCode, long created, String openId) {
        this.userId = userId;
        this.inviteCode = inviteCode;
        this.created = created;
        this.openId = openId;
    }

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 注册时间
     */
    private long created;
    /**
     * 微信小程序 openId，用于关联 预关联的用户关系的
     */
    private String openId;
}
