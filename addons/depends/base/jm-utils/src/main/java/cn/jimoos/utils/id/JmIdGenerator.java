package cn.jimoos.utils.id;


import cn.jimoos.utils.id.keygen.ShardingKeyGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangyiwen
 * @version 1.0 Created in 2020/12/11 18:01
 */
@Component
public class JmIdGenerator {
    @Resource
    ShardingKeyGenerator shardingKeyGenerator;

    public String getId() {
        return shardingKeyGenerator.generateKey().toString();
    }

}
