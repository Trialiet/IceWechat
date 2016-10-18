package cn.icedoge.controller;

import cn.icedoge.model.Wechat;
import cn.icedoge.model.WechatXML;
import cn.icedoge.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void checkSignature(Wechat wechat, PrintWriter writer) throws IOException {
        if(wechatService.check(wechat)){
            writer.print(wechat.getEchostr());
        }else {
            writer.print("Error");
        }
        writer.flush();
        writer.close();
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void dispose(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WechatXML msg = wechatService.parse(request);
        System.out.println(msg.getContent());
    }

    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
