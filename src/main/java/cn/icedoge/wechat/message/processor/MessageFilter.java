package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trialiet on 2016/10/24.
 */

public class MessageFilter {
    private Set<CommonRule> rules = new HashSet<CommonRule>();
    private MessageHandler handler;
    private MessageHandler defaultHandler = null;

    public MessageFilter(MessageHandler handler){
        this.handler = handler;
    }

    public MessageFilter(MessageHandler handler, MessageHandler defaultHandler){
        this.defaultHandler = defaultHandler;
        this.handler = handler;
    }

    public BaseMessage process(BaseMessage msg){
        for (CommonRule rule : rules){
            if(!rule.map(msg)){
                return defaultHandler == null ? null : defaultHandler.handle(msg);
            }
        }
        return handler.handle(msg);
    }

    public void addRule(CommonRule rule){
        rules.add(rule);
    }

    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }

    public void setDefaultHandler(MessageHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
}
