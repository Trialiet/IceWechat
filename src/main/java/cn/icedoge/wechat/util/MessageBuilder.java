package cn.icedoge.wechat.util;

import cn.icedoge.wechat.message.*;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import javax.servlet.ServletInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Trialiet on 2016/10/12.
 */
public class MessageBuilder {
    private String toUser = "";
    private String msgType = "";
    private static final String TEXT_TYPE = "text";
    private static final String IMAGE_TYPE = "image";
    private static final String VOICE_TYPE = "voice";
    private static final String VIDEO_TYPE = "video";
    private static final String NEWS_TYPE = "news";
    private static final String MUSIC_TYPE = "music";
    private static final String MPNEWS_TYPE = "mpnews";

    public MessageBuilder toUser(String openid){
        this.toUser = openid;
        return this;
    }

    public MessageBuilder msgType(String type){
        this.msgType = type;
        return this;
    }

    public Map build(Object o){
        Map map = new HashMap<>();
        map.put("touser", toUser);
        map.put("msgtype", msgType);
        map.put(msgType, o);
        return map;
    }

    public static BaseMessage fromInputStream(ServletInputStream inputStream){
        try {
            String str = IOUtils.toString(inputStream, "UTF-8");
            Document document = DocumentHelper.parseText(str);
            Element root = document.getRootElement();
            String type = root.element("MsgType").getText();
            byte[] bytes = type.getBytes();
            bytes[0] = (byte) (bytes[0] - 32);
            type = new String(bytes);
            Class c = Class.forName("cn.icedoge.wechat.message." + type + "Message");
            BaseMessage msg = (BaseMessage) c.newInstance();
            Iterator iterator = root.elementIterator();
            while (iterator.hasNext()){
                Element element = (Element) iterator.next();
                Method method = c.getMethod("set" + element.getName(), String.class);
                method.invoke(msg, element.getText());
            }
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
