package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

public class Remove implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TableView<String> patternsTable;

    public Remove(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute() {
        String articleName = patternsTable.getSelectionModel().getSelectedItem();
        try {
            if (connection.isOpen() && articleName != null) {
                connection.removeArticle(articleName);
                patternsTable.getItems().remove(articleName);
            } else if (!connection.isOpen()) {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            } else {
                clientWindow.showInfoDialog("Remove information", "Select article name from the table!");
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Remove warning", e.getMessage() + "!");
        }
    }
}
