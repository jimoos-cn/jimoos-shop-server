package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理查询用户表单
 *
 * @author :keepcleargas
 * @date :2021-01-15 10:42.
 */
@Data
public class UserQueryForm extends AbstractAdminPageForm4L {
    private String phone;
    private String nickname;
    private Byte role;
    private Boolean ban;
    private Long startTime;
    private Long endTime;

    public Map<String, String> toQm() {
        Map queryMap = new HashMap(8);
        queryMap.put("nickname", nickname);
        queryMap.put("role", role);
        queryMap.put("phone", phone);
        queryMap.put("ban", ban);
        queryMap.put("startTime", startTime);
        queryMap.put("endTime", endTime);
        queryMap.put("offset", offset);
        queryMap.put("limit", limit);
        return queryMap;
    }
}
