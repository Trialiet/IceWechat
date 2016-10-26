package cn.icedoge.wechat.custom;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Trialiet on 2016/10/26.
 */
public class CustomImage {
    private Map image = new TreeMap();

    public Map getImage() {
        return image;
    }

    public void setImage(Map image) {
        this.image = image;
    }

    public CustomImage(String mediaId){
        image.put("media_id", mediaId);
    }

    public void setMedia(String mediaId){
        image.put("media_id", mediaId);
    }
}
