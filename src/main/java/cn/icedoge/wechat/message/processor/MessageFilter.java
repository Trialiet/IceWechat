package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;

import java.util.HashSet;
import java.util.Set;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/**
 * Created by Trialiet on 2016/10/24.
 */
public class MessageFilter {
    private Set<CommonRule> rules = new HashSet<CommonRule>();
    public BaseMessage process(BaseMessage msg, MessageHandler handler){
        boolean flag = false;
        t:for (CommonRule rule : rules){
            switch (rule.getType()){
                case CommonRule.RULE_AND :
                    if (!rule.map(msg)){
                        return null;
                    }
                case CommonRule.RULE_OR:
                    if (rule.map(msg)){
                        flag = true;
                    }
            }
        }
        return (BaseMessage) handler.handle(msg);
    }

    public void andRule(CommonRule rule){
        rule.setType(CommonRule.RULE_AND);
        rules.add(rule);
    }

    public void orRule(CommonRule rule){
        rule.setType(CommonRule.RULE_OR);
        rules.add(rule);
    }

    public void notRule(CommonRule rule){
        rule.setType(CommonRule.RULE_NOT);
        rules.add(rule);
    }
}
