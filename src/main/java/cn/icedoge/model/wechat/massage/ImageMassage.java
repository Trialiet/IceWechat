package cn.icedoge.model.wechat.massage;

/**
 * Created by admin on 2016/10/19.
 */
public class ImageMassage extends BaseMassage {
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
