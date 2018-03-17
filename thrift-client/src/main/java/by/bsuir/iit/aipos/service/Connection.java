package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.service.excpetion.AuthorEmailException;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.*;
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
    private final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

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

    public boolean addArticle(Article article) throws ConnectionException, NameFieldException, BodyFieldException, AuthorEmailException {
        try {
            if (!isValid(article)) {
                handleInvalidArticle(article);
            }
            return client.addArticle(article);
        } catch (TException e) {
            throw new ConnectionException("Unable to add new article", e);
        }
    }

    private boolean isValid(Article article) {
        return isValidName(article.getHeader()) && isValidBody(article.getContent()) && isValidAuthorName(article.getHeader());
    }

    public boolean removeArticle(Header header) throws ConnectionException {
        try {
            return client.removeArticle(header);
        } catch (TException e) {
            throw new ConnectionException("Unable to remove article " + "\"" + header.getPatternName() + "\"", e);
        }
    }

    public Content getArticle(Header header) throws ConnectionException {
        try {
            return client.getArticle(header);
        } catch (ServiceServerException e) {
            throw new ConnectionException(e.getMessage(), e);
        } catch (TException e) {
            throw new ConnectionException("Unable to get article " + "\"" + header.getPatternName() + "\"", e);
        }
    }

    public boolean updateArticle(Header header, Article modifyArticle) throws ConnectionException, NameFieldException, BodyFieldException, AuthorEmailException {
        try {
            if (!isValid(modifyArticle)) {
                handleInvalidArticle(modifyArticle);
            }
            return client.updateArticle(header, modifyArticle);
        } catch (TException e) {
            throw new ConnectionException("Unable to updateArticle article " + "\"" + header.getPatternName() + "\"", e);
        }
    }

    private void handleInvalidArticle(Article article) throws NameFieldException, AuthorEmailException, BodyFieldException {
        if (!isValidName(article.getHeader())) {
            throw new NameFieldException("Invalid pattern name");
        } else if (!isValidAuthorName(article.getHeader())) {
            throw new AuthorEmailException("Invalid author email");
        } else {
            throw new BodyFieldException("Invalid pattern body");
        }
    }

    public List<Header> getArticleList() throws ConnectionException {
        try {
            return client.getArticleList();
        } catch (TException e) {
            throw new ConnectionException("Unable to get article list", e);
        }
    }

    private boolean isValidName(Header header) {
        header.setPatternName(defuseString(header.getPatternName()));
        return !header.getPatternName().isEmpty();
    }

    private boolean isValidBody(Content content) {
        content.setBody(defuseString(content.getBody()));
        return !content.getBody().isEmpty();
    }

    private boolean isValidAuthorName(Header header) {
        header.setAuthorEmail(defuseString(header.getAuthorEmail()));
        return header.getAuthorEmail().matches(EMAIL_REGEX);
    }

    private String defuseString(String string) {
        return string.replaceAll(SPACE_BEGIN, "").replaceAll(SPACE_MIDDLE, " ").replaceAll(SPACE_LAST, "");
    }
}
