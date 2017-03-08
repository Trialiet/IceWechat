package cn.icedoge.model;

/**
 * Created by Trialiet on 2017-3-6.
 */
public class UserConfig {
    private int user_id;
    private String wx_id;
    private String app_id;
    private String app_secret;
    private String token;
    private String access_token;

    public UserConfig(){

    }

    public UserConfig(int user_id, String wx_id, String app_id, String app_secret, String token, String access_token) {
        this.user_id = user_id;
        this.wx_id = wx_id;
        this.app_id = app_id;
        this.app_secret = app_secret;
        this.token = token;
        this.access_token = access_token;
    }

    public String getWx_id() {
        return wx_id;
    }

    public void setWx_id(String wx_id) {
        this.wx_id = wx_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
