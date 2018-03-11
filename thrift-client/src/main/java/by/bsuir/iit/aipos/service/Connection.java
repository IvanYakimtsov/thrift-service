package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.ServiceServerException;
import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

public class Connection {

    private final String SPACE_BEGIN = "^\\s+";
    private final String SPACE_MIDDLE = "\\s\\s+";
    private final String SPACE_LAST = "\\s$";

    private TTransport dataTransport;
    private WebPatternsService.Client client;

    public void open(String host, int port) throws ConnectionException {
        try {
            dataTransport = new TSocket(host, port);
            dataTransport.open();
            client = new WebPatternsService.Client(new TBinaryProtocol(dataTransport));
        } catch (TTransportException e) {
            throw new ConnectionException("Unable to connect by " + host + ":" + port, e);
        }
    }

    public void close() {
        dataTransport.close();
    }

    public boolean isOpen() {
        return dataTransport != null && dataTransport.isOpen();
    }

    public boolean addArticle(Article article) throws ConnectionException, NameFieldException, BodyFieldException {
        try {
            if (isValidName(article) && isValidBody(article)) {
                return client.addArticle(article);
            } else if (!isValidName(article)) {
                throw new NameFieldException("Invalid pattern name");
            } else {
                throw new BodyFieldException("Invalid pattern body");
            }
        } catch (TException e) {
            throw new ConnectionException("Unable to add new article", e);
        }
    }

    public boolean removeArticle(String articleName) throws ConnectionException {
        try {
            return client.removeArticle(articleName);
        } catch (TException e) {
            throw new ConnectionException("Unable to remove article " + "\"" + articleName + "\"", e);
        }
    }

    public Article getArticle(String articleName) throws ConnectionException {
        try {
            return client.getArticle(articleName);
        } catch (ServiceServerException e) {
            throw new ConnectionException(e.getMessage(), e);
        } catch (TException e) {
            throw new ConnectionException("Unable to get article " + "\"" + articleName + "\"", e);
        }
    }

    public boolean update(String articleName, Article article) throws ConnectionException, NameFieldException, BodyFieldException {
        try {
            if (isValidName(article) && isValidBody(article)) {
                return client.update(articleName, article);
            } else if (!isValidName(article)) {
                throw new NameFieldException("Invalid pattern name");
            } else {
                throw new BodyFieldException("Invalid pattern body");
            }
        } catch (TException e) {
            throw new ConnectionException("Unable to update article " + "\"" + article.getName() + "\"", e);
        }
    }

    public List<String> getArticleList() throws ConnectionException {
        try {
            return client.getArticleList();
        } catch (TException e) {
            throw new ConnectionException("Unable to get article list", e);
        }
    }

    private boolean isValidName(Article article) {
        article.setName(defuseString(article.getName()));
        return !article.getName().isEmpty();
    }

    private boolean isValidBody(Article article) {
        article.setBody(defuseString(article.getBody()));
        return !article.getBody().isEmpty();
    }

    private String defuseString(String string) {
        return string.replaceAll(SPACE_BEGIN, "").replaceAll(SPACE_MIDDLE, " ").replaceAll(SPACE_LAST, "");
    }
}
