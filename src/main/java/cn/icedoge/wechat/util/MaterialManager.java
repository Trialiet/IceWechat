package cn.icedoge.wechat.util;

import cn.icedoge.wechat.material.BaseMedia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by admin on 2016/10/24.
 */
public class MaterialManager extends WechatUtil {
    //    获取永久素材
    private static String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    private static String POST_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    //    获取素材列表
    private static String BATCHGET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

//    public WechatResponse getMaterialList(BatchgetMaterialRequest request){
//        String url = BATCHGET_MATERIAL_URL.replace("ACCESS_TOKEN", WechatConfig.getAccessToken());
//        try {
//            String data = new ObjectMapper().writeValueAsString(request);
//            return HttpPostHandler(url, data, MATERIAL_LIST_TYPE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public BaseMedia getMaterial(String media_id, String type){
        try {
            String data = new ObjectMapper().writeValueAsString(media_id);
            return (BaseMedia) HttpPostHandler(GET_MATERIAL_URL, data, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaseMedia postMaterial(BaseMedia media){
        try {
            String data = new ObjectMapper().writeValueAsString(media);
            return (BaseMedia) HttpPostHandler(POST_MATERIAL_URL, data, POST_MATERIAL_TYPE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
