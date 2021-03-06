package cn.icedoge.wechat.material;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016/10/24.
 */
public class News extends BaseMedia {
    private Set<Article> articles = new HashSet<Article>();
    private Set<Article> news_item = new HashSet<Article>();

    public void addArticle(Article article){
        if(articles.size() < 8)
            articles.add(article);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Article> getNews() {
        return news_item;
    }

    public void setNews_item(Set<Article> news_item) {
        this.news_item = news_item;
    }
}
