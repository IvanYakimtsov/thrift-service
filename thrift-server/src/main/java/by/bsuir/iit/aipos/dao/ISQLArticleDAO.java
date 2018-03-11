package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.Article;

import java.util.List;

public interface ISQLArticleDAO {
    void addArticle(Article article) throws DAOException;
    int removeArticle(String articleName) throws DAOException;
    Article getArticle(String articleName) throws DAOException;
    int update(String articleName, Article article) throws DAOException;
    List<String> getArticleList() throws DAOException;
}
