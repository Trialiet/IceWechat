package cn.icedoge.controller;

import cn.icedoge.service.ManageService;
import cn.icedoge.service.WechatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Trialiet on 2016/11/3.
 */
@Controller
public class ManageController {

    private static Logger logger = Logger.getLogger(ManageController.class);
    @Autowired
    private ManageService manageService;

    @Autowired
    private WechatService wechatService;

    @RequestMapping("/manage")
    public String homePage(){
        return "index";
    }

    @RequestMapping("/manage/menu-manage")
    public String menuManage(){
        return "template/menu-manage";
    }

    @RequestMapping("manage/common-config")
    public String commonConfig(){
        return "template/common-config";
    }


    @RequestMapping("/sidebarMenu/get")
    @ResponseBody
    public List fetchMenu(){
        List sidebarMenu = manageService.getSidebarMenu();
        return sidebarMenu;
    }
}
