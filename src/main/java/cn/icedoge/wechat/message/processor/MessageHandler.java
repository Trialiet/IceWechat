package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;

/**
 * Created by Trialiet on 2016/10/24.
 */
public interface MessageHandler {
    BaseMessage handle(BaseMessage msg);
}
