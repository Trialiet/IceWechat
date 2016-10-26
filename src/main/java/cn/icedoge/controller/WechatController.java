package cn.icedoge.controller;

import cn.icedoge.wechat.Wechat;
import cn.icedoge.service.WechatService;
import cn.icedoge.wechat.util.MessageBuilder;
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
        if(wechat.check()) return wechat.getEchostr();
        else {
            return "Invalid Access";
        }
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public String dispose(Wechat wechat, HttpServletRequest request){
        if(!wechat.check()){
            return "Invalid Access";
        }
        try {
            request.setCharacterEncoding("UTF-8");
            wechat.setMessage(MessageBuilder.fromInputStream(request.getInputStream()));
            return wechat.reply(wechatService.requestHandle(wechat));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
