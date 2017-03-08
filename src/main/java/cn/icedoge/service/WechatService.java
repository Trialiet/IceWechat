package cn.icedoge.service;


import cn.icedoge.wechat.Wechat;
import cn.icedoge.wechat.custom.CustomText;
import cn.icedoge.wechat.material.BaseMedia;
import cn.icedoge.wechat.menu.Menu;
import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.message.TextMessage;
import cn.icedoge.wechat.message.processor.MessageFilter;
import cn.icedoge.wechat.message.processor.MessageHandler;
import cn.icedoge.wechat.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Service
public class WechatService {

    @Autowired
    private CustomManager customManager;
    @Autowired
    private MenuManager menuManager;

    public Menu fetchMenu(){
        return (Menu)menuManager.fetchMenu();
    }

//    public void createMenu(Menu menu){
//        AccessToken accessToken = util.getAccess_token(APPID, SECRET);
//        util.createMenu(menu, accessToken);
//    }

    public BaseMessage requestHandle(Wechat wechat) throws Exception {
        BaseMessage recv = wechat.getMessage();
        CustomText text = new CustomText();
        String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";
        String url = oauthUrl.replace("APPID", WechatConfig.APPID).replace("SCOPE", "snsapi_base").replace("REDIRECT_URI", "http%3a%2f%2fwww.icedoge.cn%2fadmin");
        text.setContent("<a href=\"" + url +"\">测试</a>");
        text.setMsgtype("text");
        text.setTouser(wechat.getMessage().getFromUserName());
        customManager.sendMessage(text);
//        materialManager.getMaterial("-8LgvoDE84fMmAnoaA7sSwFXZ9tzuC6lmYz3sCfIlzA", "C:\\");
        MessageFilter filter = new MessageFilter(new MessageHandler() {
            @Override
            public BaseMessage handle(BaseMessage msg) {

                return null;
            }
        });
//        filter.addRule(new ContainsRule(ContainsRule.MSG_TYPE, "event"));
        return recv.route(filter);
    }
}
