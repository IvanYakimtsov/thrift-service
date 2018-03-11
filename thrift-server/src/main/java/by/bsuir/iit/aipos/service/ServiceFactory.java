package by.bsuir.iit.aipos.service;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private Server server = new Server();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Server getServer() {
        return server;
    }
}
