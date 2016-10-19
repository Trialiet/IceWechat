package cn.icedoge.util;

import cn.icedoge.model.AccessToken;
import cn.icedoge.model.wechat.Menu;
import cn.icedoge.model.wechat.WechatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class WechatUtil {
    private static Logger logger = Logger.getLogger(WechatUtil.class);
    private static final String TOKEN = "wechat";
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static Object HttpGetHandler(String url, String name){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.debug("Http Get Error");
                response.close();
                return null;
            }
            HttpEntity entity = response.getEntity();
            Object o = new ObjectMapper().readValue(EntityUtils.toString(entity), Class.forName(name));
            logger.debug(o.toString());
            response.close();
            client.close();
            return o;
        }catch (Exception e){
            logger.debug("Something wrong");
            e.printStackTrace();
        }
        return null;
    }

    private static WechatResponse HttpPostHandler(String url, String jsonData) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(jsonData, Charset.forName("utf-8")));
        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() != 200){
            System.out.println("Http Post Error");
            response.close();
            return null;
        }
        HttpEntity entity = response.getEntity();
        WechatResponse msg = new ObjectMapper().readValue(EntityUtils.toString(entity), WechatResponse.class);
        response.close();
        client.close();
        return msg;
    }

    public static Long createMenu(Menu menu, AccessToken accessToken){
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        try {
            String json = new ObjectMapper().writeValueAsString(menu);
            WechatResponse response = HttpPostHandler(url, json);
            return response.getErrcode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public static AccessToken getAccessToken(String appid, String secret)
    {
        String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", secret);
        try {
            AccessToken accessToken = (AccessToken) HttpGetHandler(url, "cn.icedoge.model.AccessToken");
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean check(String signature, Long timestamp, Long nonce){
        String[] temp = {TOKEN, timestamp+"", nonce+""};
        Arrays.sort(temp);
        String str = temp[0] + temp[1] + temp[2];
        String result = encrypt(str);
        if (result.equals(signature.toLowerCase())){
            return true;
        }
        return false;
    }

    private static String encrypt(String s){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes("UTF-8"));
            return getFormattedText(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString().toLowerCase();
    }
}
