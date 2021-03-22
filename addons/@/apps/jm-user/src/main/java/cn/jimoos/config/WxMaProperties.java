package cn.jimoos.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信小程序配置
 *
 * @author :<a href="https://github.com/keepcleargas/">keepcleargas</a>
 * @date :2020-05-12 15:14.
 */
@Data
public class WxMaProperties {
    public static final String KEY = "wx.miniApp";
    private List<Config> configs = new ArrayList<>();

    @Data
    public static class Config {
        /**
         * 设置微信小程序的appid
         */
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        private String secret;

        /**
         * 设置微信小程序消息服务器配置的token
         */
        private String token;

        /**
         * 设置微信小程序消息服务器配置的EncodingAESKey
         */
        private String aesKey;

        /**
         * 消息格式，XML或者JSON
         */
        private String msgDataFormat;
    }
}
