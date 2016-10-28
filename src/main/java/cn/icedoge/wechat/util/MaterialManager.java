package cn.icedoge.wechat.util;

import cn.icedoge.wechat.WechatResponse;
import cn.icedoge.wechat.material.BaseMedia;
import cn.icedoge.wechat.material.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by admin on 2016/10/24.
 */
@Service
public class MaterialManager extends WechatUtil {
    //    获取永久素材
    private static String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    private static String ADD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    private static String ADD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
    //    获取素材列表
    private static String BATCHGET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    private static String DEL_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";

    public static final String VIDEO_TYPE = "video";
    public static final String IMAGE_TYPE = "image";
    public static final String VOICE_TYPE = "voice";
    public static final String THUMB_TYPE  = "thumb";

    //只要不是图文消息或视频，直接提交Media ID，返回文件
    public File getMaterial(String media_id, String savePath){
        return GetMaterial(GET_MATERIAL_URL, new BaseMedia(media_id), savePath);
    }

    //获取永久图文消息
    public News getNews(String media_id){
        String data = "{ \"media_id\" : " + media_id + "}";
        return (News) HttpPostHandler(GET_MATERIAL_URL, data, NEWS_TYPE);
    }

    //上传非图文消息或视频类素材，以表单形式上传文件，返回Media ID
    public BaseMedia addMaterial(String filePath, String type){
        File file = new File(filePath);
        if (file == null || type == null || type.equals("")){
            return null;
        }
        if (!file.exists()){
            return null;
        }
        return PostMaterial(ADD_MATERIAL_URL, file, type);
    }

    //上传图文类永久素材，返回Media ID
    public BaseMedia addNews(News news){
        try {
            String data = new ObjectMapper().writeValueAsString(news);
            return (BaseMedia) HttpPostHandler(ADD_NEWS_URL, data, ADD_MATERIAL_TYPE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WechatResponse delMaterial(String media_id){
        String data = "{ \"media_id\" : " + media_id + "}";
        return HttpPostHandler(DEL_MATERIAL_URL, data);
    }

}
