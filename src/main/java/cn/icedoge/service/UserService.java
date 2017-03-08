package cn.icedoge.service;

import cn.icedoge.dao.UserDao;
import cn.icedoge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trialiet on 2017-3-8.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(String username){
        return userDao.getUser(username);
    }
}
