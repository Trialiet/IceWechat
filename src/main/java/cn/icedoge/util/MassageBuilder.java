package cn.icedoge.util;

import cn.icedoge.model.wechat.message.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Trialiet on 2016/10/12.
 */
public class MassageBuilder {

    public static BaseMessage fromInputStream(ServletInputStream inputStream) throws IOException{
        Map map = new HashMap<String, Object>();
        BaseMessage msg = null;
        Class c = null;
        String type = "";
        String str = inputStream2String(inputStream);
        try {
            if (str.length() <= 0){
                return null;
            }
            Document document = DocumentHelper.parseText(str);
            Element root = document.getRootElement();
            Element msgType = root.element("MsgType");
            switch (msgType.getText()){
                case "text" : type = "cn.icedoge.model.wechat.message.TextMessage";
                    c = Class.forName(type);
                    msg = (TextMessage) c.newInstance();
                    break;
                case "image" : type = "cn.icedoge.model.wechat.message.ImageMessage";
                    c = Class.forName(type);
                    msg = (ImageMessage) c.newInstance();
                    break;
                case "voice" : type = "cn.icedoge.model.wechat.message.VoiceMessage";
                    c = Class.forName(type);
                    msg = (VoiceMessage) c.newInstance();
                    break;
                case "video" : type = "cn.icedoge.model.wechat.message.VideoMessage";
                    c = Class.forName(type);
                    msg = (VideoMessage) c.newInstance();
                    break;
                case "location" : type = "cn.icedoge.model.wechat.message.LocationMessage";
                    c = Class.forName(type);
                    msg = (LocationMessage) c.newInstance();
                    break;
                case "link" : type = "cn.icedoge.model.wechat.message.LinkMessage";
                    c = Class.forName(type);
                    msg = (LinkMessage) c.newInstance();
                    break;
                case "event" : type = "cn.icedoge.model.wechat.message.EventMessage";
                    c = Class.forName(type);
                    msg = (EventMessage) c.newInstance();
                default : break;
            }
            Iterator iterator = root.elementIterator();
            while (iterator.hasNext()){
                Element element = (Element) iterator.next();
                Method method = c.getMethod("set" + element.getName(), String.class);
                method.invoke(msg, element.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    public static String inputStream2String(ServletInputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while( (i=in.read() ) != -1){
            baos.write(i);
        }
        return baos.toString();
    }
}
