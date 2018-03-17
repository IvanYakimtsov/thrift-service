package by.bsuir.iit.aipos.controller.command.strategy;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.AuthorEmailException;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateOldArticle implements IAddDataStrategy {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TextField nameField;
    private TextArea bodyField;
    private TableView<Header> patternsTable;

    public UpdateOldArticle(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getNameField();
        this.bodyField = mainController.getBodyField();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute(Article article) throws NameFieldException, BodyFieldException, AuthorEmailException {
        try {
            Header selectedArticle = patternsTable.getSelectionModel().getSelectedItem();
            if (selectedArticle != null && connection.updateArticle(selectedArticle, article)) {
                confirmArticleFields(article);
            } else if (selectedArticle == null) {
                clientWindow.showInfoDialog("Get information", "Select article from the table!");
            } else {
                String content = selectedArticle.getAuthorEmail() + " article \"" + selectedArticle.getPatternName() + "\" already exists!";
                clientWindow.showInfoDialog("Add article information", content);
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Update article warning", e.getMessage() + "!");
        }
    }

    private void confirmArticleFields(Article article) throws ConnectionException {
        nameField.setText(article.getHeader().getPatternName());
        bodyField.setText(article.getContent().getBody());
        patternsTable.getItems().setAll(connection.getArticleList());
    }
}
