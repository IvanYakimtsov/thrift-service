package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.controller.command.CommandDirector;
import by.bsuir.iit.aipos.controller.command.ICommand;
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
    @FXML private TableView<String> patternsTable;
    @FXML private TableColumn<String, String> nameList;
    @FXML private TextArea body;
    @FXML private TextField name, path;
    @FXML private ImageView imageView;

    private CommandDirector commandDirector;

    @FXML
    private void initialize() {
        nameList.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
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

    public TableView<String> getPatternsTable() {
        return patternsTable;
    }

    public TextArea getBody() {
        return body;
    }

    public TextField getName() {
        return name;
    }

    public TextField getPath() {
        return path;
    }

    public ImageView getImage() {
        return imageView;
    }
}
