package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.controller.command.ICommand;
import by.bsuir.iit.aipos.thrift.Header;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {

    private ClientWindow clientWindow;
    private Stage primaryStage;
    @FXML private TableView<Header> patternsTable;
    @FXML private TableColumn<Header, String> authorList;
    @FXML private TableColumn<Header, String> patternList;
    @FXML private TextArea bodyField;
    @FXML private TextField nameField, formatField;
    @FXML private ImageView patternImage;

    private CommandDirector commandDirector;

    @FXML
    private void initialize() {
        authorList.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthorEmail()));
        patternList.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPatternName()));
    }

    public void buttonHandler(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        Command commandName = Command.valueOf(button.getId().toUpperCase());
        ICommand command = commandDirector.getCommand(commandName);
        command.execute();
    }

    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
        patternsTable.setItems(clientWindow.getObservableNameList());
        commandDirector = new CommandDirector(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ClientWindow getClientWindow() {
        return clientWindow;
    }

    public TableView<Header> getPatternsTable() {
        return patternsTable;
    }

    public TextArea getBodyField() {
        return bodyField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getFormatField() {
        return formatField;
    }

    public ImageView getImage() {
        return patternImage;
    }

}
