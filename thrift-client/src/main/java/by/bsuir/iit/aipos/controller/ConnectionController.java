package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectionController {

    private ClientWindow clientWindow;
    @FXML private TextField host;
    @FXML private TextField port;
    private Stage connectionStage;

    private String hostValue;
    private String portValue;
    private boolean okClicked = false;

    public void setConnectionStage(Stage connectionStage) {
        this.connectionStage = connectionStage;
    }

    public void okHandle(ActionEvent actionEvent) {
        hostValue = host.getText();
        portValue = port.getText();
        okClicked = true;
        connectionStage.close();
    }

    public void closeHandle(ActionEvent actionEvent) {
        okClicked = false;
        connectionStage.close();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    public void setParameters(String hostValue, String portValue) {
        this.hostValue = hostValue;
        this.portValue = portValue;
    }
}
