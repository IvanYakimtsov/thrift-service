package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.controller.command.strategy.IAddDataStrategy;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ImageConverter;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;
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
    private ImageView imageView;
    private TextField formatField;

    public AddData(MainController mainController, IAddDataStrategy addDataStrategy) {
        this.addDataStrategy = addDataStrategy;
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getName();
        this.bodyField = mainController.getBody();
        this.imageView = mainController.getImage();
        this.formatField = mainController.getPath();
    }

    @Override
    public void execute() {
        try {
            if (connection.isOpen() && imageView.getImage() != null) {
                Article article = prepareArticle();
                addDataStrategy.execute(article);
            } else if (!connection.isOpen()) {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            } else {
                handleInvalidImage();
            }
        } catch (IOException e) {
            clientWindow.showWarningDialog("Format warning", "Input parameters d'nt match the pattern!");
        } catch (NameFieldException e) {
            handleNameException(e.getMessage());
        } catch (BodyFieldException e) {
            handleBodyException(e.getMessage());
        }
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
        imageView.requestFocus();
    }

    private Article prepareArticle() throws IOException {
        Article article = new Article();
        article.setName(nameField.getText());
        article.setBody(bodyField.getText());
        article.setImage(imageConverter.toByteArray(imageView.getImage(), formatField.getText()));
        article.setImageFormat(formatField.getText());
        return article;
    }

}
