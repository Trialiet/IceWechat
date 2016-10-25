package cn.icedoge.wechat.custom;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trialiet on 2016/10/25.
 */
public class CustomNews extends BaseCustom {
    private Set news = new HashSet();

    public Set getNews() {
        return news;
    }

    public void setNews(Set news) {
        this.news = news;
    }

    public CustomNews addArticle(CustomArticle article){
        if (news.size() < 10) {
            news.add(article);
        }
        return this;
    }
}
