package cn.icedoge.model.wechat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2016/10/19.
 */

@XmlRootElement(name = "xml")
public class VideoMessage extends BaseMessage {
    private String MediaId;
    private String ThumbMediaId;

    @XmlElement
    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @XmlElement
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
