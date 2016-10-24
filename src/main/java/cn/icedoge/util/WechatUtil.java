package cn.icedoge.util;

import cn.icedoge.dao.ConfigDao;
import cn.icedoge.wechat.json.AccessToken;
import cn.icedoge.wechat.json.BatchgetMaterialRequest;
import cn.icedoge.wechat.xml.Menu;
import cn.icedoge.wechat.json.WechatResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;

/**
 * Created by Trialiet on 2016/10/19.
 */

@Component
public class WechatUtil {
    private static Logger logger = Logger.getLogger(WechatUtil.class);
    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
//    获取永久素材
    private static String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
//    获取素材列表
    private static String BATCHGET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    private static final int DEFAULT_TYPE = 0;
    private static final int MATERIAL_LIST_TYPE = 1;
    @Autowired
    private ConfigDao configDao;


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

    private WechatResponse HttpPostHandler(String url, String jsonData) throws Exception {
        return HttpPostHandler(url, jsonData, DEFAULT_TYPE);
    }

    private WechatResponse HttpPostHandler(String url, String jsonData, int type) throws Exception {
        Class c = null;
        WechatResponse msg = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(jsonData, Charset.forName("utf-8")));
        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() != 200){
            logger.error("Http Post Error");
            response.close();
            return null;
        }
        HttpEntity entity = response.getEntity();
        switch (type){
            case DEFAULT_TYPE : c = Class.forName("cn.icedoge.wechat.json.WechatResponse");
                msg = (WechatResponse) new ObjectMapper().readValue(EntityUtils.toString(entity), c);
                break;
        }
        response.close();
        client.close();
        return msg;
    }

    public WechatResponse getMaterialList(BatchgetMaterialRequest request, String accessToken){
        String url = BATCHGET_MATERIAL_URL.replace("ACCESS_TOEN", accessToken);
        try {
            String data = new ObjectMapper().writeValueAsString(request);
            return HttpPostHandler(url, data, MATERIAL_LIST_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WechatResponse createMenu(Menu menu, AccessToken accessToken){
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        try {
            String data = new ObjectMapper().writeValueAsString(menu);
            WechatResponse response = HttpPostHandler(url, data);
            logger.debug("Create Menu: " + response.getErrcode());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
