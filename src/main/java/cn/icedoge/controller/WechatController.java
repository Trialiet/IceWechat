package cn.icedoge.controller;

import cn.icedoge.model.wechat.Wechat;
import cn.icedoge.model.wechat.message.BaseMessage;
import cn.icedoge.model.wechat.message.TextMessage;
import cn.icedoge.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        } finally {
        }
        return "Error";
    }

    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
