package cn.icedoge.util;

import cn.icedoge.model.wechat.json.AccessToken;
import cn.icedoge.model.wechat.xml.Menu;
import cn.icedoge.model.wechat.json.WechatResponse;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Trialiet on 2016/10/19.
 */

@Component
public class WechatUtil {
    private static Logger logger = Logger.getLogger(WechatUtil.class);
    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private WechatResponse HttpGetHandler(String url){
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
            WechatResponse wechatResponse = new ObjectMapper().readValue(EntityUtils.toString(entity), WechatResponse.class);
            response.close();
            client.close();
            return wechatResponse;
        }catch (Exception e){
            logger.debug("Something wrong");
            e.printStackTrace();
        }
        return null;
    }

    private WechatResponse HttpPostHandler(String url, String jsonData) throws IOException {
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

    public Long createMenu(Menu menu, AccessToken accessToken){
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        try {
            String json = new ObjectMapper().writeValueAsString(menu);
            WechatResponse response = HttpPostHandler(url, json);
            return response != null ? response.getErrcode() : -1L;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public AccessToken getAccessToken(String appid, String secret)
    {
        String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", secret);
        try {
            return (AccessToken) HttpGetHandler(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
