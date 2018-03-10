package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.dao.config.ConnectionPool;
import by.bsuir.iit.aipos.dao.config.DBParameter;
import by.bsuir.iit.aipos.thrift.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SQLArticleDAO implements ISQLArticleDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private ResourceBundle dbBundle = ResourceBundle.getBundle(DBParameter.BASE_NAME);

    @Override
    public boolean addArticle(Article article) {
        return false;
    }

    @Override
    public boolean removeArticle(String articleName) {
        return false;
    }

    @Override
    public Article getArticle(String articleName) {
        return null;
    }

    @Override
    public boolean update(String articleName, Article article) {
        return false;
    }

    @Override
    public List<String> getArticleList() {
        return null;
    }
}
