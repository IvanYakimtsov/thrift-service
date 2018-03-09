package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.service.Server;
import by.bsuir.iit.aipos.view.ServerWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    private ServerWindow serverWindow;
    private Stage primaryStage;
    @FXML private TextField portField;
    @FXML private CheckBox statusBox;
    @FXML private TextArea logInfo;

    public void buttonHandler(ActionEvent actionEvent) {
        Server server = new Server();
        server.start(7911);
    }

    public ServerWindow getServerWindow() {
        return serverWindow;
    }

    public void setServerWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
