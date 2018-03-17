package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

public class Remove implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TableView<Header> patternsTable;

    public Remove(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute() {
        Header selectedArticle = patternsTable.getSelectionModel().getSelectedItem();
        try {
            if (connection.isOpen() && selectedArticle != null && connection.removeArticle(selectedArticle)) {
                patternsTable.getItems().remove(selectedArticle);
            } else if (!connection.isOpen()) {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            } else if (selectedArticle == null){
                clientWindow.showInfoDialog("Remove information", "Select article name from the table!");
            } else {
                String content = selectedArticle.getAuthorEmail() + " article \"" + selectedArticle.getPatternName() + "\" does not exists!";
                clientWindow.showInfoDialog("Remove information", content);
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Remove warning", e.getMessage() + "!");
        }
    }
}
