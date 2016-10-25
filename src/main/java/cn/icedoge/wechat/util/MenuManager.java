package cn.icedoge.wechat.util;

import cn.icedoge.wechat.sys.AccessToken;
import cn.icedoge.wechat.WechatResponse;
import cn.icedoge.wechat.menu.Menu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by admin on 2016/10/24.
 */
public class MenuManager extends WechatUtil {
    private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public WechatResponse createMenu(Menu menu){
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", WechatConfig.getAccessToken());
        try {
            String data = new ObjectMapper().writeValueAsString(menu);
            return HttpPostHandler(url, data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}