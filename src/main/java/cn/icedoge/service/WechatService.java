package cn.icedoge.service;


import cn.icedoge.wechat.json.AccessToken;
import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.message.TextMessage;
import cn.icedoge.wechat.message.processor.MessageFilter;
import cn.icedoge.wechat.message.processor.MessageHandler;
import cn.icedoge.wechat.message.processor.ContainsRule;
import cn.icedoge.wechat.xml.Menu;
import cn.icedoge.util.WechatConfig;
import cn.icedoge.util.WechatUtil;
import cn.icedoge.util.MassageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Service
public class WechatService {

    @Autowired
    private WechatUtil util;
    private static final String APPID = "wx1aaad9abb1e3e1b3";
    private static final String SECRET = "8a2888a41b3662ee9ae4b152bfd6f46e";

    public void createMenu(Menu menu){
        AccessToken accessToken = util.getAccessToken(APPID, SECRET);
        util.createMenu(menu, accessToken);
    }

    public BaseMessage requestHandle(HttpServletRequest request) throws Exception {
        BaseMessage msg = MassageBuilder.fromInputStream(request.getInputStream());
        String openid = msg.getFromUserName();
        MessageFilter filter = new MessageFilter();
        filter.andRule(new ContainsRule(ContainsRule.MSG_TYPE, "event"));
        BaseMessage resp = filter.process(msg, new MessageHandler() {
            @Override
            public BaseMessage handle(BaseMessage msg) {
                TextMessage resp = new TextMessage();
                resp.setFromUserName(WechatConfig.FROM_USER_NAME);
                resp.setToUserName(openid);
                resp.setContent(msg.getMsgType());
                return resp;
            }
        });
        return resp;
    }
}
