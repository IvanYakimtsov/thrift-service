package by.bsuir.iit.aipos.service;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private Connection connection = new Connection();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
