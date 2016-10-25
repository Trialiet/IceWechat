package cn.icedoge.wechat.message;

import java.util.Set;

/**
 * Created by Trialiet on 2016/10/25.
 */
public class NewsMessage extends BaseMessage {
    private int ArticleCount;
    private String Title;
    private Set Articles;

    @Override
    public String toXML(){
        StringBuilder stringBuilder = new StringBuilder();
        ArticleCount = Articles.size();

        return stringBuilder.toString();
    }
}
