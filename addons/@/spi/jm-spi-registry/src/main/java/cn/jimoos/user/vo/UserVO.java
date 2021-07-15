package cn.jimoos.user.vo;

import cn.jimoos.user.model.UserSession;
import lombok.Data;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-23 11:22
 */
@Data
public class UserVO {
    private Long id;

    private String avatar;

    private String phone;

    private String nickname;

    private Byte gender;

    /**
     * 唯一邀请码
     */
    private String inviteCode;

    /**
     * 0 用户 1 合伙人 2 高级合伙人
     */
    private byte role;

    private Short ban;

    private Long birthday;

    private Long createAt;

    private Long updateAt;

    private String province;

    private String city;

    private String area;

    private Boolean deleted;
    private UserSession session;
    /**
     * 额外的对象
     */
    private Map extras;

    public UserVO() {
    }

    public UserVO(Long userId, Byte role) {
        this.id = userId;
        this.role = role;
    }

    public void transformBanValue(Boolean ban) {
        if (ban) {
            this.ban = 1;
        } else {
            this.ban = 0;
        }
    }
}
