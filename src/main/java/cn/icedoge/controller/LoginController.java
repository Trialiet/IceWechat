package cn.icedoge.controller;

import cn.icedoge.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Trialiet on 2017-3-8.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login/auth", method = RequestMethod.POST)
    public String login(User user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), user.getUser_password());
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        if (currentUser.isAuthenticated()){
            return "index";
        }else {
            return "error";
        }
    }
    @RequestMapping("/login")
    public String nav(){
        return "login";
    }
}
