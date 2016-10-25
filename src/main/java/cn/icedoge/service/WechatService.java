package cn.icedoge.service;


import cn.icedoge.wechat.Wechat;
import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.message.TextMessage;
import cn.icedoge.wechat.message.processor.MessageFilter;
import cn.icedoge.wechat.message.processor.MessageHandler;
import cn.icedoge.wechat.message.processor.ContainsRule;
import org.springframework.stereotype.Service;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Service
public class WechatService {

//    public void createMenu(Menu menu){
//        AccessToken accessToken = util.getAccessToken(APPID, SECRET);
//        util.createMenu(menu, accessToken);
//    }

    public BaseMessage requestHandle(Wechat wechat) throws Exception {
        BaseMessage recv = wechat.getMessage();
        MessageFilter filter = new MessageFilter(new MessageHandler() {
            @Override
            public BaseMessage handle(BaseMessage msg) {
                TextMessage resp = new TextMessage();
                resp.setContent(msg.getMsgType());
                return resp;
            }
        });
//        filter.addRule(new ContainsRule(ContainsRule.MSG_TYPE, "event"));
        return recv.route(filter);
    }
}
