package cn.icedoge.service;


import cn.icedoge.wechat.Wechat;
import cn.icedoge.wechat.custom.CustomText;
import cn.icedoge.wechat.material.BaseMedia;
import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.message.TextMessage;
import cn.icedoge.wechat.message.processor.MessageFilter;
import cn.icedoge.wechat.message.processor.MessageHandler;
import cn.icedoge.wechat.util.CustomManager;
import cn.icedoge.wechat.util.MaterialManager;
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
    private MaterialManager materialManager;
//    public void createMenu(Menu menu){
//        AccessToken accessToken = util.getAccessToken(APPID, SECRET);
//        util.createMenu(menu, accessToken);
//    }

    public BaseMessage requestHandle(Wechat wechat) throws Exception {
        BaseMessage recv = wechat.getMessage();
        CustomText text = new CustomText();
        text.setContent("客服消息");
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
