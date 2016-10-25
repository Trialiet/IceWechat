package cn.icedoge.wechat.sys;

import cn.icedoge.wechat.WechatResponse;

/**
 * Created by Trialiet on 2016/10/18.
 */
public class AccessToken extends WechatResponse {
    private String access_token;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expire_in) {
        this.expires_in = expire_in;
    }
}
