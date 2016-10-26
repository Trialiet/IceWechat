package cn.icedoge.wechat.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Trialiet on 2016/10/26.
 */
public class MessageManager extends WechatUtil {
    private static String SEND_ALL_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    private Map filter = new TreeMap();
    private Set touser = new HashSet();
    private String msgtype;


    public Map getFilter() {
        return filter;
    }

    public void setFilter(Map filter) {
        this.filter = filter;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
