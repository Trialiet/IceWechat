package cn.icedoge.wechat.material;

import java.io.File;

/**
 * Created by admin on 2016/10/24.
 */
public class MediaPost {
    private MediaForm media;
    private VideoForm introduction;
    private String type;
    private File file;

    public MediaForm getMedia() {
        return media;
    }

    public void setMedia(MediaForm media) {
        this.media = media;
    }

    public VideoForm getIntroduction() {
        return introduction;
    }

    public void setIntroduction(VideoForm introduction) {
        this.introduction = introduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
