package cn.icedoge.service;


import cn.icedoge.dao.ConfigDao;
import cn.icedoge.model.wechat.json.AccessToken;
import cn.icedoge.model.wechat.message.BaseMessage;
import cn.icedoge.model.wechat.message.TextMessage;
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
    @Autowired
    private ConfigDao configDao;
    private static final String APPID = "wx1aaad9abb1e3e1b3";
    private static final String SECRET = "8a2888a41b3662ee9ae4b152bfd6f46e";

    public void createMenu(Menu menu){
        AccessToken accessToken = util.getAccessToken(APPID, SECRET);
        util.createMenu(menu, accessToken);
    }

    public BaseMessage requestHandle(HttpServletRequest request) throws Exception {
        BaseMessage msg = MassageBuilder.fromInputStream(request.getInputStream());
        String localName = msg.getToUserName();
        String openid = msg.getFromUserName();
        TextMessage textMassage = new TextMessage();
        textMassage.setToUserName(openid);
        textMassage.setFromUserName(localName);
        textMassage.setMsgType("text");
        textMassage.setContent(msg.getMsgType());
        return textMassage;
    }
}
