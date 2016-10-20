package cn.icedoge.util;

import cn.icedoge.model.wechat.massage.*;
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

    public static BaseMassage fromInputStream(ServletInputStream inputStream) throws IOException{
        Map map = new HashMap<String, Object>();
        BaseMassage msg = null;
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
                case "text" : type = "cn.icedoge.model.wechat.massage.TextMassage";
                    c = Class.forName(type);
                    msg = (TextMassage) c.newInstance();
                    break;
                case "image" : type = "cn.icedoge.model.wechat.massage.ImageMassage";
                    c = Class.forName(type);
                    msg = (ImageMassage) c.newInstance();
                    break;
                case "voice" : type = "cn.icedoge.model.wechat.massage.VoiceMassage";
                    c = Class.forName(type);
                    msg = (VoiceMassage) c.newInstance();
                    break;
                case "video" : type = "cn.icedoge.model.wechat.massage.VideoMassage";
                    c = Class.forName(type);
                    msg = (VideoMassage) c.newInstance();
                    break;
                case "location" : type = "cn.icedoge.model.wechat.massage.LocationMassage";
                    c = Class.forName(type);
                    msg = (LocationMassage) c.newInstance();
                    break;
                case "link" : type = "cn.icedoge.model.wechat.massage.LinkMassage";
                    c = Class.forName(type);
                    msg = (LinkMassage) c.newInstance();
                    break;
                case "event" : type = "cn.icedoge.model.wechat.massage.EventMassage";
                    c = Class.forName(type);
                    msg = (EventMassage) c.newInstance();
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
