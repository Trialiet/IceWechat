package cn.icedoge.model.wechat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2016/10/19.
 */

@XmlRootElement(name = "xml")
public class LocationMessage extends BaseMessage {
    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;

    @XmlElement
    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    @XmlElement
    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    @XmlElement
    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    @XmlElement
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
