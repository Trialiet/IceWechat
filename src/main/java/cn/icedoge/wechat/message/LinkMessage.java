package cn.icedoge.wechat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2016/10/19.
 */

@XmlRootElement(name = "xml")
public class LinkMessage extends BaseMessage {
    private String Title;
    private String Description;
    private String Url;

    @XmlElement
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @XmlElement
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @XmlElement
    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
