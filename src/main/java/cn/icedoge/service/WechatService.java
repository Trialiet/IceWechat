package cn.icedoge.service;


import cn.icedoge.model.wechat.json.AccessToken;
import cn.icedoge.model.wechat.massage.BaseMassage;
import cn.icedoge.model.wechat.massage.TextMassage;
import cn.icedoge.model.wechat.xml.Menu;
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
    private static final String appid = "wx1aaad9abb1e3e1b3";
    private static final String secret = "8a2888a41b3662ee9ae4b152bfd6f46e";

    public void createMenu(Menu menu){
        AccessToken accessToken = util.getAccessToken(appid, secret);
        util.createMenu(menu, accessToken);
    }

    public BaseMassage requestHandle(HttpServletRequest request) throws Exception {
        BaseMassage msg = MassageBuilder.fromInputStream(request.getInputStream());
        String localName = msg.getToUserName();
        String openid = msg.getFromUserName();
        TextMassage textMassage = new TextMassage();
        textMassage.setToUserName(openid);
        textMassage.setFromUserName(localName);
        textMassage.setMsgType("text");
        textMassage.setContent(msg.getMsgType());
        return textMassage;
    }
}
