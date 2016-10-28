package cn.icedoge.wechat.util;

import cn.icedoge.wechat.sys.AccessToken;
import cn.icedoge.wechat.WechatResponse;
import cn.icedoge.wechat.menu.Menu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/10/24.
 */
@Service
public class MenuManager extends WechatUtil {
    private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public WechatResponse createMenu(Menu menu){
        try {
            String data = new ObjectMapper().writeValueAsString(menu);
            return HttpPostHandler(CREATE_MENU_URL, data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
