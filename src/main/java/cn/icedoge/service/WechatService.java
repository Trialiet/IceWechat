package cn.icedoge.service;


import cn.icedoge.model.AccessToken;
import cn.icedoge.model.wechat.Menu;
import cn.icedoge.model.wechat.Wechat;
import cn.icedoge.model.WechatXML;
import cn.icedoge.util.WechatUtil;
import cn.icedoge.util.XMLParser;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Service
public class WechatService {
    private static final String appid = "wx1aaad9abb1e3e1b3";
    private static final String secret = "8a2888a41b3662ee9ae4b152bfd6f46e";

    public boolean check(Wechat wechat) throws IOException {
        Long timestamp = wechat.getTimestamp();
        Long nonce = wechat.getNonce();
        String signature = wechat.getSignature();
        return WechatUtil.check(signature, timestamp, nonce);
    }

    public void createMenu(Menu menu){
        AccessToken accessToken = WechatUtil.getAccessToken(appid, secret);
        WechatUtil.createMenu(menu, accessToken);
    }

    public WechatXML requestHandle(HttpServletRequest request) throws Exception {
        WechatXML msg = XMLParser.parse(request.getInputStream());
        String localName = msg.getToUserName();
        String openid = msg.getFromUserName();
        WechatXML reply = new WechatXML();
        reply.setToUserName(openid);
        reply.setFromUserName(localName);
        reply.setMsgType("text");
        reply.setContent(msg.getMsgType());
        reply.setCreateTime(String.valueOf(System.currentTimeMillis()/1000L));
        return reply;
    }
}
