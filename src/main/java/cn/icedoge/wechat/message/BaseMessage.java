package cn.icedoge.wechat.message;

import cn.icedoge.wechat.message.processor.MessageFilter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by admin on 2016/10/19.
 */
@XmlRootElement(name = "xml")
public class BaseMessage implements Serializable{
    private String ToUserName;
    private String FromUserName;
    private String MsgType;
    private String MsgId;
    private String CreateTime;

    @XmlElement
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    @XmlElement
    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    @XmlElement
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @XmlElement
    public String getCreateTime() {
        return CreateTime;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?> c = this.getClass();
        Method[] methods = c.getMethods();
        stringBuilder.append("<xml>");
        try {
            for (Method method : methods) {
                if (method.getName().contains("get") && !method.getName().contains("Class")) {
                    String name = method.getName().substring(3);
                    String str = (String) method.invoke(this);
                    stringBuilder.append("<").append(name);
                    stringBuilder.append("><![CDATA[");
                    stringBuilder.append(str);
                    stringBuilder.append("]]></");
                    stringBuilder.append(name).append(">");
                }
            }
            stringBuilder.append("</xml>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public BaseMessage route(MessageFilter[] filters){
        for(MessageFilter filter : filters){
            BaseMessage reply = filter.process(this);
            if (reply != null) return reply;
        }
        return null;
    }

    public BaseMessage route(MessageFilter filter){
        return route(new MessageFilter[] {filter});
    }
}
