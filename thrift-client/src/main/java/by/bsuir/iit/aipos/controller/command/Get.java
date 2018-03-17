package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.service.Connection;
import by.bsuir.iit.aipos.service.ImageConverter;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.service.excpetion.ConnectionException;
import by.bsuir.iit.aipos.thrift.Content;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Get implements ICommand {

    private Connection connection = ServiceFactory.getInstance().getConnection();
    private ImageConverter imageConverter = ServiceFactory.getInstance().getImageConverter();

    private ClientWindow clientWindow;
    private TextField nameField;
    private TextArea bodyField;
    private ImageView patternImage;
    private TextField formatField;
    private TableView<Header> patternsTable;

    public Get(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.nameField = mainController.getNameField();
        this.bodyField = mainController.getBodyField();
        this.patternImage = mainController.getImage();
        this.patternsTable = mainController.getPatternsTable();
        this.formatField = mainController.getFormatField();
    }

    @Override
    public void execute() {
        Header selectedArticle = patternsTable.getSelectionModel().getSelectedItem();
        try {
            Content content;
            if (connection.isOpen() && selectedArticle != null && (content = connection.getArticle(selectedArticle)) != null) {
                confirmArticleFields(selectedArticle.getPatternName(), content);
            } else if (!connection.isOpen()) {
                clientWindow.showInfoDialog("Connected information", "Client is not connected!");
            } else if (selectedArticle == null) {
                clientWindow.showInfoDialog("Get information", "Select article name from the table!");
            } else {
                String contentMessage = selectedArticle.getAuthorEmail() + " article \"" + selectedArticle.getPatternName() + "\" already exists!";
                clientWindow.showInfoDialog("Remove information", contentMessage);
            }
        } catch (ConnectionException e) {
            clientWindow.showWarningDialog("Get warning", e.getMessage() + "!");
        }
    }

    private void confirmArticleFields(String patternName, Content content) {
        try {
            nameField.setText(patternName);
            bodyField.setText(content.getBody());
            patternImage.setImage(imageConverter.toImage(content.getImage()));
            formatField.setText(content.getImageFormat());
        } catch (IOException e) {
            clientWindow.showInfoDialog("Get warning", "Unable to set article image!");
        }
    }
}
