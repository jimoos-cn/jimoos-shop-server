package cn.jimoos.utils.id;

import cn.jimoos.utils.id.keygen.ShardingKeyGenerator;
import cn.jimoos.utils.id.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author wangyiwen
 * @version 1.0 Created in 2020/4/16 12:06
 */
@Configuration
public class IdConfiguration {

    @Bean
    public ShardingKeyGenerator shardingKeyGenerator() {
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        Properties properties = new Properties();
        properties.setProperty("worker.id", String.valueOf(initWorkerId()));
        properties.setProperty("max.tolerate.time.difference.milliseconds", "60000");
        snowflakeShardingKeyGenerator.setProperties(properties);
        return snowflakeShardingKeyGenerator;
    }

    //生成当前机器的workerId
    private long initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        long workerId = 0L;
        // IPV4
        if (ipAddressByteArray.length == 4) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0xFF;
            }
            // IPV6
        } else if (ipAddressByteArray.length == 16) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0B111111;
            }
        } else {
            throw new IllegalStateException("Bad LocalHost InetAddress, please check your network!");
        }
        return workerId;
    }
}
