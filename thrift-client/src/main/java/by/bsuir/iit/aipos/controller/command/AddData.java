package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.controller.command.strategy.IAddDataStrategy;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ImageConverter;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.AuthorEmailException;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.Content;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AddData implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();
    private ImageConverter imageConverter = ServiceFactory.getInstance().getImageConverter();

    private IAddDataStrategy addDataStrategy;

    private ClientWindow clientWindow;
    private TextField nameField;
    private TextArea bodyField;
    private ImageView patternImage;
    private TextField formatField;

    public AddData(MainController mainController, IAddDataStrategy addDataStrategy) {
        this.addDataStrategy = addDataStrategy;
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getNameField();
        this.bodyField = mainController.getBodyField();
        this.patternImage = mainController.getImage();
        this.formatField = mainController.getFormatField();
    }

    @Override
    public void execute() {
        try {
            StringBuilder authorEmail = new StringBuilder();
            if (isAddDataImpossible(authorEmail)) {
                Article article = prepareArticle(authorEmail.toString());
                addDataStrategy.execute(article);
            } else if (!connection.isOpen()) {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            } else if (patternImage.getImage() == null){
                handleInvalidImage();
            }
        } catch (IOException e) {
            clientWindow.showWarningDialog("Format warning", "Input parameters do not match the pattern!");
        } catch (NameFieldException e) {
            handleNameException(e.getMessage());
        } catch (BodyFieldException e) {
            handleBodyException(e.getMessage());
        } catch (AuthorEmailException e) {
            clientWindow.showWarningDialog("Invalid parameters", e.getMessage() + "!");
        }
    }

    private boolean isAddDataImpossible(StringBuilder authorEmail) {
        return connection.isOpen() &&
               patternImage.getImage() != null &&
               clientWindow.showInputDialog("Input dialog", "Input author email: ", authorEmail);
    }

    private void handleNameException(String message) {
        clientWindow.showWarningDialog("Invalid parameters", message + "!");
        nameField.requestFocus();
    }

    private void handleBodyException(String message) {
        clientWindow.showWarningDialog("Invalid parameters", message + "!");
        bodyField.requestFocus();
    }

    private void handleInvalidImage() {
        clientWindow.showWarningDialog("Invalid parameters", "Invalid image field!");
        patternImage.requestFocus();
    }

    private Article prepareArticle(String authorEmail) throws IOException {
        Article article = new Article();
        article.setHeader(prepareHeader(authorEmail));
        article.setContent(prepareContent());
        return article;
    }

    private Header prepareHeader(String authorEmail) {
        Header header = new Header();
        header.setAuthorEmail(authorEmail);
        header.setPatternName(nameField.getText());
        return header;
    }

    private Content prepareContent() throws IOException {
        Content content = new Content();
        content.setBody(bodyField.getText());
        content.setImage(imageConverter.toByteArray(patternImage.getImage(), formatField.getText()));
        content.setImageFormat(formatField.getText());
        return content;
    }
}
