package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

public class Disconnect implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private TableView<String> tableView;

    public Disconnect(TableView<String> tableView) {
        this.tableView = tableView;
    }

    @Override
    public void execute() {
        connection.close();
        tableView.getItems().clear();
    }
}
