package cn.icedoge.model.wechat.massage;

/**
 * Created by admin on 2016/10/19.
 */
public class EventMassage extends BaseMassage {
    private String Event;
    private String EventKey;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
