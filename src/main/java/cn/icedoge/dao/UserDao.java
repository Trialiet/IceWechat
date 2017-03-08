package cn.icedoge.dao;

import cn.icedoge.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Trialiet on 2017-3-8.
 */
@Repository
public interface UserDao {
    @Select("SELECT * FROM user WHERE user_name = #{username}")
    User getUser(String username);
}
