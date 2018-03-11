package by.bsuir.iit.aipos.dao.exception;

public class NoSuchArticleException extends Exception {
    private static final long serialVersionUID = -3315271119407506140L;

    public NoSuchArticleException() {
    }

    public NoSuchArticleException(String message) {
        super(message);
    }

    public NoSuchArticleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchArticleException(Throwable cause) {
        super(cause);
    }
}
