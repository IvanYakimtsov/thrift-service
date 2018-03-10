package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.thrift.Article;

import java.util.List;

public interface ISQLArticleDAO {
    boolean addArticle(Article article);
    boolean removeArticle(String articleName);
    Article getArticle(String articleName);
    boolean update(String articleName, Article article);
    List<String> getArticleList();
}
