package cn.icedoge.wechat.material;

/**
 * Created by admin on 2016/10/24.
 */
public class Video extends BaseMedia{
    private String title;
    private String description;
    private String down_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }
}
