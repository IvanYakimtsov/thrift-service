package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.Content;
import by.bsuir.iit.aipos.thrift.Header;

import java.util.List;

public interface ISQLArticleDAO {
    void addArticle(Article article) throws DAOException;
    void removeArticle(Header header) throws DAOException;
    Content getArticle(Header header) throws DAOException;
    void updateArticle(Header header, Article modifyArticle) throws DAOException;
    List<Header> getArticleList() throws DAOException;
}
