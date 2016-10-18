package cn.icedoge.service;


import cn.icedoge.model.AccessToken;
import cn.icedoge.model.Wechat;
import cn.icedoge.model.WechatXML;
import cn.icedoge.util.AccessUtil;
import cn.icedoge.util.SignUtil;
import cn.icedoge.util.XMLParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Service
public class WechatService {

    public boolean check(Wechat wechat) throws IOException {
        Long timestamp = wechat.getTimestamp();
        Long nonce = wechat.getNonce();
        String signature = wechat.getSignature();
        AccessToken accessToken = AccessUtil.getAccessToken();
        if(accessToken != null){
            accessToken.getAccess_token();
            accessToken.getExpire_in();
        }
        return SignUtil.check(signature, timestamp, nonce);
    }

    public WechatXML parse(HttpServletRequest request) throws IOException {
        WechatXML msg = XMLParser.parse(request.getInputStream());
        return msg;
    }
}
