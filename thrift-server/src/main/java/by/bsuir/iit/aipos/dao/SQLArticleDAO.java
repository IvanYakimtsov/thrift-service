package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.dao.config.ConnectionPool;
import by.bsuir.iit.aipos.dao.config.DBParameter;
import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SQLArticleDAO implements ISQLArticleDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final int NAME_INDEX = 1;
    private final int BODY_INDEX = 2;
    private final int IMAGE_INDEX = 3;
    private final int IMAGE_FORMAT_INDEX = 4;
    private final int PREVIOUS_NAME_INDEX = 5;

    private ResourceBundle dbBundle = ResourceBundle.getBundle(DBParameter.BASE_NAME);

    public SQLArticleDAO() {
        connectionPool.initPoolData();
    }

    @Override
    public void addArticle(Article article) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.ADD_ARTICLE));
            setStatement(statement, article);
            statement.executeUpdate();
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to add article to the database", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public int removeArticle(String articleName) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.REMOVE_ARTICLE));
            statement.setString(NAME_INDEX, articleName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to remove article from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Article getArticle(String articleName) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.GET_ARTICLE));
            statement.setString(NAME_INDEX, articleName);
            resultSet = statement.executeQuery();
            return handleSet(resultSet, articleName);
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to get article " + "\"" + articleName + "\" from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public int update(String articleName, Article article) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.UPDATE_ARTICLE));
            setStatement(statement, article, articleName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to update article " + "\"" + articleName + "\"", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public List<String> getArticleList() throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.GET_ARTICLE_LIST));
            resultSet = statement.executeQuery();
            return handleSet(resultSet);
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to get article list from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    private void setStatement(PreparedStatement statement, Article article) throws SQLException {
        statement.setString(NAME_INDEX, article.getName());
        statement.setString(BODY_INDEX, article.getBody());
        statement.setBytes(IMAGE_INDEX, article.getImage());
        statement.setString(IMAGE_FORMAT_INDEX, article.getImageFormat());
    }

    private void setStatement(PreparedStatement statement, Article article, String articleName) throws SQLException {
        setStatement(statement, article);
        statement.setString(PREVIOUS_NAME_INDEX, articleName);
    }

    private Article handleSet(ResultSet resultSet, String articleName) throws SQLException {
        if (resultSet.next()) {
            Article article = new Article();
            article.setName(resultSet.getString(NAME_INDEX));
            article.setBody(resultSet.getString(BODY_INDEX));
            article.setImage(resultSet.getBytes(IMAGE_INDEX));
            article.setImageFormat(resultSet.getString(IMAGE_FORMAT_INDEX));
            return article;
        } else {
            return null;
        }
    }

    private List<String> handleSet(ResultSet resultSet) throws SQLException {
        List<String> articleList = new ArrayList<>();
        while (resultSet.next()) {
            String articleName = resultSet.getString(NAME_INDEX);
            articleList.add(articleName);
        }
        return articleList;
    }
}
