package cn.icedoge.util;

import cn.icedoge.model.WechatXML;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.ServletInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Trialiet on 2016/10/12.
 */
public class XMLParser {

    public static WechatXML parse(ServletInputStream inputStream) throws IOException{
        WechatXML msg = null;
        String str = inputStream2String(inputStream);
        try {
            if (str.length() <=0 || str == null){
                return null;
            }
            Document document = DocumentHelper.parseText(str);
            Element root = document.getRootElement();
            Iterator iterator = root.elementIterator();
            msg = new WechatXML();
            Class<?> c = Class.forName("Model.WechatXML");
            msg = (WechatXML) c.newInstance();
            while (iterator.hasNext()){
                Element element = (Element) iterator.next();
                Field field = c.getDeclaredField(element.getName());
                Method method = c.getDeclaredMethod("set" + element.getName(), field.getType());
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
