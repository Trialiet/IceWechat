package cn.icedoge.wechat.util;

import cn.icedoge.wechat.sys.AccessToken;
import cn.icedoge.wechat.WechatResponse;
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

/**
 * Created by Trialiet on 2016/10/19.
 */

@Component
public class WechatUtil {
    private static Logger logger = Logger.getLogger(WechatUtil.class);
    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    public static final String DEFAULT_TYPE = "cn.icedoge.wechat.WechatResponse";
//    public static final int MATERIAL_LIST_TYPE = 1;
    public static final String MEDIA_TYPE = "cn.icedoge.wechat.material.BaseMedia";
    public static final String NEWS_TYPE = "cn.icedoge.wechat.material.News";
    public static final String VIDEO_TYPE = "cn.icedoge.wechat.material.Video";
    public static final String POST_MATERIAL_TYPE = "cn.icedoge.wechat.material.BaseMedia";



    protected WechatResponse HttpGetHandler(String url){
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
            WechatResponse wechatResponse = new ObjectMapper().readValue(EntityUtils.toString(entity), AccessToken.class);
            response.close();
            client.close();
            return wechatResponse;
        }catch (Exception e){
            logger.debug("Something wrong");
            e.printStackTrace();
        }
        return null;
    }

    protected WechatResponse HttpPostHandler(String url, String jsonData){
        return HttpPostHandler(url, jsonData, DEFAULT_TYPE);
    }

    protected WechatResponse HttpPostHandler(String url, String jsonData, String type){
        WechatResponse msg = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(jsonData, Charset.forName("utf-8")));
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Http Post Error");
                response.close();
                return null;
            }
            HttpEntity entity = response.getEntity();
            msg = (WechatResponse) new ObjectMapper().readValue(EntityUtils.toString(entity), Class.forName(type));
            if(msg.getErrcode() == 40014 ){
                String newToken = getAccessToken().getAccess_token();
                String newUrl = url.replace(WechatConfig.getAccessToken(), newToken);
                WechatConfig.setAccessToken(newToken);
                return HttpPostHandler(newUrl, jsonData, type);
            }
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    public AccessToken getAccessToken()
    {
        String url = ACCESS_TOKEN_URL.replace("APPID", WechatConfig.getConfig(WechatConfig.APPID))
                .replace("SECRET", WechatConfig.getConfig(WechatConfig.SECRET));
        try {
            return (AccessToken) HttpGetHandler(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}