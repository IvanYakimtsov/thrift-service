package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ConnectionException;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

public class Connect implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TableView<String> tableView;

    public Connect(ClientWindow clientWindow, TableView<String> tableView) {
        this.clientWindow = clientWindow;
        this.tableView = tableView;
    }

    @Override
    public void execute() {
        String host = "", port = "";
        if (clientWindow.showConnectionDialog(host, port)) {
            try {
                connection.open(host, Integer.parseInt(port));
                tableView.getItems().addAll(connection.getArticleList());
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }
    }

}
