package cn.icedoge.wechat.message.processor;

import cn.icedoge.wechat.message.BaseMessage;

/**
 * Created by Trialiet on 2016/10/24.
 */
public abstract class CommonRule {
    public static final int RULE_AND = 1;
    public static final int RULE_OR = 2;
    public static final int RULE_NOT = 3;
    protected int type;

    public abstract boolean map(BaseMessage msg);

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
