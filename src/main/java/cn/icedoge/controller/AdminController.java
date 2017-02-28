package cn.icedoge.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Trialiet on 2016/11/3.
 */
@Controller
public class AdminController {

    private static Logger logger = Logger.getLogger(AdminController.class);
    @RequestMapping("admin")
    public String test(String code){
        logger.debug("Request test.html");
        return "test";
    }
}
