package by.bsuir.iit.aipos.controller.command.strategy;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.AuthorEmailException;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.Content;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNewArticle implements IAddDataStrategy {

    private Connection connection = ServiceFactory.getInstance().getConnection();

    private ClientWindow clientWindow;
    private TextField nameField;
    private TextArea bodyField;
    private TableView<Header> patternsTable;

    public AddNewArticle(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getNameField();
        this.bodyField = mainController.getBodyField();
        this.patternsTable = mainController.getPatternsTable();
    }

    @Override
    public void execute(Article article) throws NameFieldException, BodyFieldException, AuthorEmailException {
        try {
            if (connection.addArticle(article)) {
                confirmArticleFields(article);
            } else {
                Header header = article.getHeader();
                String content = header.getAuthorEmail() + " article \"" + header.getPatternName() + "\" already exists!";
                clientWindow.showInfoDialog("Add article information", content);
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Add article warning", e.getMessage() + "!");
        }
    }

    private void confirmArticleFields(Article article) throws ConnectionException {
        nameField.setText(article.getHeader().patternName);
        bodyField.setText(article.getContent().getBody());
        patternsTable.getItems().add(article.getHeader());
    }
}
