package cn.icedoge.wechat.custom;

import cn.icedoge.wechat.WechatResponse;

/**
 * Created by Trialiet on 2016/10/25.
 */
public class BaseCustom extends WechatResponse{
    private String touser;
    private String msgtype;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
