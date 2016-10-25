package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Trialiet on 2016/10/24.
 */
public class ContainsRule implements CommonRule{
    private String key;
    private String value;

    public static final String MSG_TYPE = "MsgType";
    public static final String TEXT_CONTENT = "Content";
    public static final String FROM_USER = "FromUserName";
    public static final String EVENT_KEY = "EventKey";
    public static final String CREATE_TIME = "CreateTime";

    public ContainsRule(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean map(BaseMessage msg){
        try {
            Method method = msg.getClass().getMethod("get" + key);
            String str = (String) method.invoke(msg);
            if (str.contains(value)) return true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
