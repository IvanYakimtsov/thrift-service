package by.bsuir.iit.aipos.dao;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private ISQLArticleDAO isqlArticleDAO = new SQLArticleDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public ISQLArticleDAO getIsqlArticleDAO() {
        return isqlArticleDAO;
    }
}
