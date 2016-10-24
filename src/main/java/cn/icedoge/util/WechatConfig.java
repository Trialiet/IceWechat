package cn.icedoge.util;

/**
 * Created by Trialiet on 2016/10/20.
 */
public class WechatConfig {
    private static String TOKEN = "wechat";
    private static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String APP_ID = "APPID";
    private static final String APP_SECRET = "SECRET";
    public static final String FROM_USER_NAME = "gh_1b5af629a433";

    public static String getAppSecret() {
        return APP_SECRET;
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        WechatConfig.TOKEN = TOKEN;
    }
}
