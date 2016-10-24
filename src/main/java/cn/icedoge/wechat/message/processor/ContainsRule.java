package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Trialiet on 2016/10/24.
 */
public class ContainsRule extends CommonRule{
    private String key;
    private String value;

    public static final String MSG_TYPE = "MsgType";
    public static final String FROM_USER = "FromUserName";
    public static final String EVENT_KEY = "EventKey";
    public static final String CREATE_TIME = "CreateTime";


    public ContainsRule(String key, String value, int type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public ContainsRule(String key, String value){
        this.key = key;
        this.value = value;
        this.type = RULE_AND;
    }

    @Override
    public boolean map(BaseMessage msg){
        try {
            Method method = msg.getClass().getMethod("get" + key);
            String str = (String) method.invoke(msg);
            switch (this.type){
                case RULE_AND : if (str.contains(value)) return true;
                    break;
                case RULE_NOT : if (!str.contains(value)) return true;
                    break;
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
