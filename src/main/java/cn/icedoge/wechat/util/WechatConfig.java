package cn.icedoge.wechat.util;

import cn.icedoge.dao.UserConfigDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Properties;

/**
 * Created by Trialiet on 2016/10/20.
 */
public class WechatConfig {
    public static final String TOKEN = "user_config_token";
    public static final String APPID = "user_config_app_id";
    public static final String SECRET = "user_config_app_secret";
    private static String ACCESS_TOKEN = "user_config_access_token";
//    private static Properties properties;
    @Autowired
    private static UserConfigDao userConfigDao;

//    static {
//        properties = new Properties();
//        InputStream stream = WechatConfig.class.getClassLoader().getResourceAsStream("wechat.properties");
//        try {
//            properties.load(stream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static String getToken(String wxid){
        return userConfigDao.getToken(wxid);
    }

//    public static String getConfig(String name){
//        return properties.getProperty(name);
////    }

    public static String getConfig(String wxid, String configName){
        return userConfigDao.getConfigByName(wxid, configName);
    }

    public static String getAccessToken(String wxid) {
        return userConfigDao.getAccessToken(wxid);
    }

    public static void setWechatConfig(String wxid, String configName, String configValue){
        userConfigDao.updateWechatConfig(wxid, configName, configValue);
    }

    public static void setAccessToken(String wxid, String accessToken) {
        userConfigDao.updateAccessToken(wxid, accessToken);
    }
}
