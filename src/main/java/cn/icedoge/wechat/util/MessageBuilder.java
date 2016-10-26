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
