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

    private StringBuilder hostValue;
    private StringBuilder portValue;
    private boolean okClicked = false;

    private final String IPV4_REGEXP = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
    private final String PORT_REGEXP = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";

    public void setConnectionStage(Stage connectionStage) {
        this.connectionStage = connectionStage;
    }

    public void okHandle(ActionEvent actionEvent) {
        if (host.getText().matches(IPV4_REGEXP) && port.getText().matches(PORT_REGEXP)) {
            hostValue.append(host.getText());
            portValue.append(port.getText());
            okClicked = true;
            connectionStage.close();
        } else {
            clientWindow.shoWarningDialog("Invalid parameters", "Invalid host and port value!");
        }
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

    public void setParameters(StringBuilder hostValue, StringBuilder portValue) {
        this.hostValue = hostValue;
        this.portValue = portValue;
    }
}
