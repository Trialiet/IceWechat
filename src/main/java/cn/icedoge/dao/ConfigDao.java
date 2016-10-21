package cn.icedoge.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Trialiet on 2016/10/21.
 */

@Repository
public interface ConfigDao {

    @Select("SELECT config_value FROM config WHERE config_name = #{configName}")
    String selectByName(String configName);
}
