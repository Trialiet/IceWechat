package cn.icedoge.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Trialiet on 2017-3-6.
 */
@Repository
public interface UserConfigDao {
    @Insert("INSERT INTO user_config(wx_id, app_id, app_secret, token)" +
            "values(#{wxId), #{appId}, #{appSecret}, #{token})")
    String addWechat(String wxId, String appId, String appSecret, String token, String accessToken);

    @Select("SELECT access_token FROM user_config WHERE wx_id = #{wxId}")
    String getAccessToken(String wxId);

    @Select("SELECT token FROM user_config WHERE wx_id = #{wxId}")
    String getToken(String wxId);

    @Select("SELECT app_id FROM user_config WHERE wx_id = #{wxId}")
    String getAppId(String wxId);

    @Select("SELECT app_secret FROM user_config WHERE wx_id = #{wxId}")
    String getAppSecret(String wxId);

    @Update("UPDATE user_config SET access_token = #{accessToken} WHERE wx_id = #{wxId}")
    String updateAccessToken(String wxId, String accessToken);

    @Update("UPDATE user_config SET app_secret = #{appSecret} WHERE wx_id = #{wxId}")
    String updateAppSecret(String wxId, String appSecret);

    @Update("UPDATE user_config SET #{configName} = #{configValue} WHERE wx_id = #{wxId}")
    String updateWechatConfig(String wxId, String configName, String configValue);

    @Select("SELECT #{configName} FROM user_config WHERE wx_id = #{wxId}")
    String getConfigByName(String wxId, String configName);
}
