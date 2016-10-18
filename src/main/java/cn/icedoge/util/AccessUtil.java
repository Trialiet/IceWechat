package cn.icedoge.util;

import cn.icedoge.model.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by Trialiet on 2016/10/18.
 */
public class AccessUtil{
    private static final String appid = "wx1aaad9abb1e3e1b3";
    private static final String secret = "8a2888a41b3662ee9ae4b152bfd6f46e";
    public static AccessToken getAccessToken() throws IOException
    {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() > 300){
            response.close();
            return null;
        }
        HttpEntity entity = response.getEntity();
        AccessToken accessToken = new ObjectMapper().readValue(EntityUtils.toString(entity), AccessToken.class);
        response.close();
        return accessToken;
    }
}
