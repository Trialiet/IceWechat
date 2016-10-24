package cn.icedoge.controller;

import cn.icedoge.wechat.Wechat;
import cn.icedoge.wechat.message.BaseMessage;
import cn.icedoge.wechat.xml.CommonButton;
import cn.icedoge.wechat.xml.Menu;
import cn.icedoge.wechat.xml.ParentButton;
import cn.icedoge.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Trialiet on 2016/10/12.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WechatService wechatService;

    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public String checkSignature(Wechat wechat) throws IOException {
        CommonButton button1 = new CommonButton();
        button1.setName("Test1");
        button1.setType("click");
        button1.setKey("btn1");
        CommonButton button2 = new CommonButton();
        button2.setName("Test2");
        button2.setType("click");
        button2.setKey("btn2");
        CommonButton[] buttons = {button1, button2};
        ParentButton parentButton = new ParentButton();
        parentButton.setName("Test");
        parentButton.setSub_button(buttons);
        ParentButton[] parentButtons = {parentButton};
        Menu menu = new Menu();
        menu.setButton(parentButtons);
        wechatService.createMenu(menu);
        if(wechat.check()) return wechat.getEchostr();
        else {
            return "Invalid Access";
        }
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public String dispose(Wechat wechat, HttpServletRequest request)
            throws IOException{
        request.setCharacterEncoding("UTF-8");
        if(!wechat.check()){
            return "Invalid Access";
        }
        try {
            BaseMessage reply = wechatService.requestHandle(request);
            return reply.toXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
