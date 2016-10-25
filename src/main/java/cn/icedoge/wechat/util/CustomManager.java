package cn.icedoge.wechat.util;

import cn.icedoge.wechat.custom.BaseCustom;
import cn.icedoge.wechat.WechatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Trialiet on 2016/10/25.
 */
public class CustomManager extends WechatUtil {
    private static final String SEND_CUSTOM_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    public WechatResponse sendMessage(BaseCustom msg){
        String url = SEND_CUSTOM_MESSAGE_URL.replace("ACCESS_TOKEN", WechatConfig.getAccessToken());
        try {
            String data = new ObjectMapper().writeValueAsString(msg);
            return HttpPostHandler(url, data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
