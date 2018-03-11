package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.service.Server;
import by.bsuir.iit.aipos.service.ServiceFactory;
import by.bsuir.iit.aipos.view.ServerWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class MainController {

    private final String PORT_REGEXP = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
    private final String CRLF = "\r\n";

    private Server server = ServiceFactory.getInstance().getServer();

    private ServerWindow serverWindow;
    private Stage primaryStage;
    @FXML private TextField portField;
    @FXML private CheckBox statusBox;
    @FXML private TextArea logInfo;

    public MainController() {
        server.setController(this);
    }

    public void startHandler(ActionEvent actionEvent) {
        if (!server.isServing() && portField.getText().matches(PORT_REGEXP)) {
            handleStart();
        } else if (server.isServing()) {
            serverWindow.showInfoDialog("Start information", "Server is already start!");
        } else {
            serverWindow.showWarningDialog("Start warning", "Invalid port value!");
        }
    }

    public void stopHandler(ActionEvent actionEvent) {
        if (server.isServing()) {
            handleStop();
        } else {
            serverWindow.showInfoDialog("Start information", "Server is already stop!");
        }
    }

    private void handleStart() {
        server.start(Integer.parseInt(portField.getText()));
        portField.setDisable(true);
        statusBox.setSelected(true);
        logInfo.appendText(new Date() + ": server started." + CRLF);
    }

    private void handleStop() {
        server.stop();
        portField.setDisable(false);
        statusBox.setSelected(false);
        logInfo.appendText(new Date() + ": server stopped." + CRLF);
    }

    public synchronized void log(String message) {
        logInfo.appendText(new Date() + ": " + message + "." + CRLF);
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
