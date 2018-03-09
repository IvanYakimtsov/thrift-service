package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

public class Disconnect implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TableView<String> tableView;

    public Disconnect(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.tableView = mainController.getPatternsTable();
    }

    @Override
    public void execute() {
        if (connection.isOpen()) {
            connection.close();
            tableView.getItems().clear();
            clientWindow.showInfoDialog("Disconnect information", "Disconnected!");
        } else {
            clientWindow.showInfoDialog("Disconnect information", "Client is not connected!");
        }
    }
}
