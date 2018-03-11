package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.controller.ServerController;
import by.bsuir.iit.aipos.dao.DAOFactory;
import by.bsuir.iit.aipos.dao.ISQLArticleDAO;
import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.ServiceServerException;
import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.TException;

import java.util.List;

public class ServiceHandler implements WebPatternsService.Iface {

    private ServerController serverController;

    private ISQLArticleDAO articleDAO = DAOFactory.getInstance().getIsqlArticleDAO();

    @Override
    public boolean addArticle(Article article) throws TException {
        try {
            if (articleDAO.getArticle(article.getName()) == null) {
                articleDAO.addArticle(article);
                serverController.log("add new article \"" + article.getName() + "\"");
                return true;
            } else {
                return false;
            }
        } catch (DAOException e) {
            serverController.log(e.getMessage());
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public boolean removeArticle(String articleName) throws TException {
        try {
            boolean removeStatus = articleDAO.removeArticle(articleName) != 0;
            serverController.log("remove article \"" + articleName + "\"");
            return  removeStatus;
        } catch (DAOException e) {
            serverController.log(e.getMessage());
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public Article getArticle(String articleName) throws TException {
        try {
            return articleDAO.getArticle(articleName);
        } catch (DAOException e) {
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public boolean update(String articleName, Article article) throws TException {
        try {
            boolean updateStatus = articleDAO.update(articleName, article) != 0;
            serverController.log("update article \"" + articleName + "\"");
            return updateStatus;
        } catch (DAOException e) {
            serverController.log(e.getMessage());
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public List<String> getArticleList() throws TException {
        try {
            return articleDAO.getArticleList();
        } catch (DAOException e) {
            throw new ServiceServerException(e.getMessage());
        }
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
