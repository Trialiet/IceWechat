package cn.icedoge.model.wechat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2016/10/19.
 */

@XmlRootElement(name = "xml")
public class TextMessage extends BaseMessage {

    private String Content;

    @XmlElement
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
