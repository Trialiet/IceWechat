package cn.icedoge.wechat.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by Trialiet on 2016/10/20.
 */
public class WechatConfig {
    public static final String TOKEN = "token";
    public static final String APPID = "appid";
    public static final String SECRET = "secret";
    public static final String FROM_USER_NAME = "fromUserName";
    private static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static Properties properties;

    static {
        properties = new Properties();
        InputStream stream = WechatConfig.class.getClassLoader().getResourceAsStream("wechat.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfig(String name){
        return properties.getProperty(name);
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }
}
