package cn.icedoge.wechat;

import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.util.WechatConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Trialiet on 2016/10/12.
 */

public class Wechat {
    private static final String TOKEN = "wechat";
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private Long timestamp;
    private Long nonce;
    private String signature;
    private String echostr;
    private String openid;
    private BaseMessage message;

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String reply(BaseMessage msg){
        if (msg == null){
            return "";
        }
        msg.setFromUserName(message.getToUserName());
        msg.setToUserName(openid);
        msg.setCreateTime(String.valueOf(System.currentTimeMillis()/1000L));
        return msg.toXML();
    }

    public void reply(BaseMessage msg, HttpServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(reply(msg));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean check(){
        return check(TOKEN);
    }

    public boolean check(String token){
        String[] temp = {token, this.timestamp+"", this.nonce+""};
        Arrays.sort(temp);
        String str = temp[0] + temp[1] + temp[2];
        String result = encrypt(str);
        if (result.equals(signature.toLowerCase())){
            return true;
        }
        return false;
    }

    private String encrypt(String s){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes("UTF-8"));
            return getFormattedText(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString().toLowerCase();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public BaseMessage getMessage() {
        return message;
    }

    public void setMessage(BaseMessage message) {
        this.message = message;
    }
}
