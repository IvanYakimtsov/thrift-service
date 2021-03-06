package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;

import java.util.List;

public class UpdateTable implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TableView<Header> patternsTable;

    public UpdateTable(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute() {
        try {
            if (connection.isOpen()) {
                List<Header> articleList = connection.getArticleList();
                patternsTable.getItems().setAll(articleList);
            } else {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Table updateArticle warning", e.getMessage() + "!");
        }
    }
}
