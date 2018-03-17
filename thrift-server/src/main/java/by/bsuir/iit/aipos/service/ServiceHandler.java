package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.controller.ServerController;
import by.bsuir.iit.aipos.dao.DAOFactory;
import by.bsuir.iit.aipos.dao.ISQLArticleDAO;
import by.bsuir.iit.aipos.dao.exception.DAOException;
import by.bsuir.iit.aipos.thrift.*;
import org.apache.thrift.TException;

import java.util.List;

public class ServiceHandler implements WebPatternsService.Iface {

    private ServerController serverController;

    private ISQLArticleDAO articleDAO = DAOFactory.getInstance().getIsqlArticleDAO();

    @Override
    public boolean addArticle(Article article) throws TException {
        try {
            Header header = article.getHeader();
            if (articleDAO.getArticle(header) == null) {
                articleDAO.addArticle(article);
                serverController.log(header.authorEmail + " add new article \"" + header.getPatternName() + "\"");
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
    public boolean removeArticle(Header header) throws TException {
        try {
            if (articleDAO.getArticle(header) != null) {
                articleDAO.removeArticle(header);
                serverController.log("remove article " + header.getAuthorEmail() +  " \"" + header.getPatternName() + "\"");
                return  true;
            } else {
                return false;
            }
        } catch (DAOException e) {
            serverController.log(e.getMessage());
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public Content getArticle(Header header) throws TException {
        try {
            return articleDAO.getArticle(header);
        } catch (DAOException e) {
            throw new ServiceServerException(e.getMessage());
        }
    }

    @Override
    public boolean updateArticle(Header header, Article modifyArticle) throws TException {
        try {
            if (articleDAO.getArticle(modifyArticle.getHeader()) == null) {
                articleDAO.updateArticle(header, modifyArticle);
                Header modifyHeader = modifyArticle.getHeader();
                serverController.log(modifyHeader.getAuthorEmail() +
                        " update article " + header.getAuthorEmail() + " \"" + header.getPatternName() + "\"" +
                        ", new article header: " + modifyHeader.getAuthorEmail() + " \"" + modifyHeader.getPatternName() + "\"");
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
    public List<Header> getArticleList() throws TException {
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
