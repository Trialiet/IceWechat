package cn.icedoge.model.wechat.massage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by admin on 2016/10/19.
 */
public class BaseMassage {
    protected String ToUserName;
    protected String FromUserName;
    protected String MsgType;
    protected String MsgId;
    private String CreateTime;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?> c = this.getClass();
        Method[] methods = c.getMethods();
        stringBuilder.append("<xml>");
        try {
            for (Method method : methods) {
                if (method.getName().contains("get") && !method.getName().contains("MsgId") && !method.getName().contains("Class")) {
                    String name = method.getName().substring(3);
                    String str = (String) method.invoke(this);
                    stringBuilder.append("<").append(name);
                    stringBuilder.append("><![CDATA[");
                    stringBuilder.append(str);
                    stringBuilder.append("]]></");
                    stringBuilder.append(name).append(">");
                }
            }
            stringBuilder.append("<CreateTime>").append(System.currentTimeMillis()/1000L).append("</CreateTime>");
            stringBuilder.append("</xml>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
