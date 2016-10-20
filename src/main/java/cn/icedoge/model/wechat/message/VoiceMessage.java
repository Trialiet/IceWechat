package cn.icedoge.model.wechat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2016/10/19.
 */

@XmlRootElement(name = "xml")
public class VoiceMessage extends BaseMessage {
    private String MediaId;
    private String Format;
    private String Recognition;

    @XmlElement
    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @XmlElement
    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    @XmlElement
    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
