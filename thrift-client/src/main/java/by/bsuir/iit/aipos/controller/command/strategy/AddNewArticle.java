package by.bsuir.iit.aipos.controller.command.strategy;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNewArticle implements IAddDataStrategy {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TextField nameField;
    private TextArea bodyField;
    private TableView<String> patternsTable;

    public AddNewArticle(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getNameField();
        this.bodyField = mainController.getBodyField();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute(Article article) throws NameFieldException, BodyFieldException {
        try {
            if (connection.addArticle(article)) {
                confirmArticleFields(article);
            } else {
                clientWindow.showInfoDialog("Add article information", "Article \"" + article.getName() + "\" already exists!");
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
            clientWindow.showWarningDialog("Add article warning", e.getMessage() + "!");
        }
    }

    private void confirmArticleFields(Article article) {
        nameField.setText(article.getName());
        bodyField.setText(article.getBody());
        patternsTable.getItems().add(article.getName());
    }
}
