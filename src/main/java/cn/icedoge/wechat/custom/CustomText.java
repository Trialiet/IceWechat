package cn.icedoge.wechat.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trialiet on 2016/10/25.
 */
public class CustomText extends BaseCustom {
    private Map text = new HashMap();

    public Map getText() {
        return text;
    }

    public void setText(Map text) {
        this.text = text;
    }

    public void setContent(String content){
        text.put("content", content);
    }
}
