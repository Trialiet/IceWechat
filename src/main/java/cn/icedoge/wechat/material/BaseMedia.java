package cn.icedoge.wechat.material;

import cn.icedoge.wechat.WechatResponse;

/**
 * Created by Trialiet on 2016/10/21.
 */
public class BaseMedia extends WechatResponse{
    private String media_id;
    private String url;

    public BaseMedia() {
    }

    public BaseMedia(String media_id) {
        this.media_id = media_id;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
