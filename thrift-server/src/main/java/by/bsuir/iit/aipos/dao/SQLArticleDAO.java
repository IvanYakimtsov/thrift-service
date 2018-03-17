package by.bsuir.iit.aipos.dao;

import by.bsuir.iit.aipos.dao.config.ConnectionPool;
import by.bsuir.iit.aipos.dao.config.DBParameter;
import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.Content;
import by.bsuir.iit.aipos.thrift.Header;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SQLArticleDAO implements ISQLArticleDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

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
    public void removeArticle(Header header) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.REMOVE_ARTICLE));
            statement.setString(1, header.getAuthorEmail());
            statement.setString(2, header.getPatternName());
            statement.executeUpdate();
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to remove article from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Content getArticle(Header header) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.GET_ARTICLE));
            statement.setString(1, header.getAuthorEmail());
            statement.setString(2, header.getPatternName());
            resultSet = statement.executeQuery();
            return handleGetArticle(resultSet);
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to get article " + "\"" + header.getPatternName() + "\" from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void updateArticle(Header header, Article modifyArticle) throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.UPDATE_ARTICLE));
            setStatement(statement, header, modifyArticle);
            statement.executeUpdate();
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to update article " + "\"" + header.getPatternName() + "\"", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public List<Header> getArticleList() throws DAOException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(dbBundle.getString(DBParameter.GET_ARTICLE_LIST));
            resultSet = statement.executeQuery();
            return handleGetArticleList(resultSet);
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            throw new DAOException("Unable to get article list from database", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    private void setStatement(PreparedStatement statement, Article article) throws SQLException {
        statement.setString(1, article.getHeader().getAuthorEmail());
        statement.setString(2, article.getHeader().getPatternName());
        statement.setString(3, article.getContent().getBody());
        statement.setBytes(4, article.getContent().getImage());
        statement.setString(5, article.getContent().getImageFormat());
    }

    private void setStatement(PreparedStatement statement, Header header, Article modifyArticle) throws SQLException {
        setStatement(statement, modifyArticle);
        statement.setString(6, header.getAuthorEmail());
        statement.setString(7, header.getPatternName());
    }

    private Content handleGetArticle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Content content = new Content();
            content.setBody(resultSet.getString(1));
            content.setImage(resultSet.getBytes(2));
            content.setImageFormat(resultSet.getString(3));
            return content;
        } else {
            return null;
        }
    }

    private List<Header> handleGetArticleList(ResultSet resultSet) throws SQLException {
        List<Header> articleList = new ArrayList<>();
        while (resultSet.next()) {
            Header header = new Header();
            header.setAuthorEmail(resultSet.getString(1));
            header.setPatternName(resultSet.getString(2));
            articleList.add(header);
        }
        return articleList;
    }
}
