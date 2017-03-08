package cn.icedoge.wechat.util;

import cn.icedoge.wechat.material.BaseMedia;
import cn.icedoge.wechat.sys.AccessToken;
import cn.icedoge.wechat.WechatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * Created by Trialiet on 2016/10/19.
 */

@Component
public class WechatUtil {

    private static Logger logger = Logger.getLogger(WechatUtil.class);
    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    public static final String DEFAULT_TYPE = "cn.icedoge.wechat.WechatResponse";
    public static final String ACCESS_TOKEN_TYPE = "cn.icedoge.wechat.sys.AccessToken";
    public static final String NEWS_TYPE = "cn.icedoge.wechat.material.News";
    public static final String VIDEO_TYPE = "cn.icedoge.wechat.material.Video";
    public static final String ADD_MATERIAL_TYPE = "cn.icedoge.wechat.material.BaseMedia";
    public static final String MENU_TYPE = "cn.icedoge.wechat.menu.Menu";

    protected WechatResponse HttpGetHandler(String path, String type){
        WechatResponse msg;
        String url = path;
        //如果不是获取token的请求，就替换掉URL里的token
        if(!type.equals(ACCESS_TOKEN_TYPE)){
            url = path.replace("ACCESS_TOKEN", WechatConfig.getAccessToken("icedog"));
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            //如果网络有问题（状态码不是200），就返回null
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.debug("Http Get Error");
                response.close();
                return null;
            }
            //获取微信服务器响应的entity
            HttpEntity entity = response.getEntity();
            msg = (WechatResponse) new ObjectMapper().readValue(EntityUtils.toString(entity), Class.forName(type));
            //如果entity的信息表示token过期，就获取新token重新请求
            if(msg.getErrcode() != null && (msg.getErrcode() == 40014 || msg.getErrcode() == 42001 || msg.getErrcode() == 40001)){
                //获取新token
                String newToken = getAccessToken().getAccess_token();
                url = url.replace("ACCESS_TOKEN", newToken);
                //把新token存到配置文件
                WechatConfig.setAccessToken("icedog", newToken);
                httpGet.releaseConnection();
                client.close();
                return HttpGetHandler(url, type);
            }
            response.close();
            client.close();
            return msg;
        }catch (Exception e){
            logger.debug("Something wrong");
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    protected WechatResponse HttpGetHandler(String path){
        return HttpGetHandler(path, ACCESS_TOKEN_TYPE);
    }

    protected WechatResponse HttpPostHandler(String url, String jsonData){
        return HttpPostHandler(url, jsonData, DEFAULT_TYPE);
    }

    protected WechatResponse HttpPostHandler(String path, String jsonData, String type){
        WechatResponse msg = null;
        CloseableHttpClient client = null;
        String url = path.replace("ACCESS_TOKEN", WechatConfig.getAccessToken("icedog"));
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(jsonData, Charset.forName("utf-8")));
        CloseableHttpResponse response = null;
        try {
            if (url.startsWith("https")){
                client = createSSLInsecureClient();
            }else {
                client = HttpClients.createDefault();
            }
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Http Post Error");
                response.close();
                return null;
            }
            HttpEntity entity = response.getEntity();
            msg = (WechatResponse) new ObjectMapper().readValue(EntityUtils.toString(entity), Class.forName(type));
            if(msg.getErrcode() != null && (msg.getErrcode() == 40014 || msg.getErrcode() == 42001 || msg.getErrcode() == 40001)){
                String newToken = getAccessToken().getAccess_token();
                url = path.replace("ACCESS_TOKEN", newToken);
                WechatConfig.setAccessToken("icedog", newToken);
                post.releaseConnection();
                client.close();
                return HttpPostHandler(url, jsonData, type);
            }
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            post.releaseConnection();
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    public File GetMaterial(String path, BaseMedia baseMedia, String filePath){
        String accessToken = WechatConfig.getAccessToken("icedog");
        String url = path.replace("ACCESS_TOKEN", accessToken);
        File file = null;
        InputStream stream = null;
        FileOutputStream fileOutputStream = null;
        HttpPost post = new HttpPost(url);
        String mediaId = baseMedia.getMedia_id();
        try {
            post.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(baseMedia), Charset.forName("UTF-8")));
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(post);
            try {
                String str = response.getFirstHeader("Content-disposition").getValue();
                String ext = str.substring(str.lastIndexOf("."), str.length() - 1);
                stream = response.getEntity().getContent();
                file = new File(filePath + mediaId + ext);
                fileOutputStream = new FileOutputStream(file);
                int c = stream.read();
                while (c != -1) {
                    fileOutputStream.write(c);
                    c = stream.read();
                }
            }catch (Exception e){
                logger.debug(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public BaseMedia PostMaterial(String path, File file, String type){
        BaseMedia msg = null;
        String access_token = WechatConfig.getAccessToken("icedog");
        String url = path.replace("ACCESS_TOKEN", access_token);
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(file);
        builder.addPart("media", fileBody);
        builder.addPart("access_token", new StringBody(access_token, ContentType.TEXT_PLAIN));
        builder.addPart("type", new StringBody(type, ContentType.TEXT_PLAIN));
        HttpEntity entity = builder.build();
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Cache-Control", "no-cache");
        post.setEntity(entity);
        try {
            CloseableHttpClient client = createSSLInsecureClient();
            CloseableHttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 200){
                logger.debug("Http Connect Error");
                response.close();
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            msg = new ObjectMapper().readValue(EntityUtils.toString(responseEntity), BaseMedia.class);
            if(msg.getErrcode() != null && (msg.getErrcode() == 40014 || msg.getErrcode() == 42001 || msg.getErrcode() == 40001)){
                String newToken = getAccessToken().getAccess_token();
                String newUrl = url.replace(access_token, newToken);
                WechatConfig.setAccessToken("icedog", newToken);
                post.releaseConnection();
                client.close();
                return PostMaterial(newUrl, file, type);
            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private AccessToken getAccessToken()
    {
        String url = ACCESS_TOKEN_URL.replace("APPID", WechatConfig.getConfig("icedog", WechatConfig.APPID))
                .replace("SECRET", WechatConfig.getConfig("icedog", WechatConfig.SECRET));
        try {
            return (AccessToken) HttpGetHandler(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    return true;
                }

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String s, java.security.cert.X509Certificate x509Certificate) throws SSLException {

                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
}
