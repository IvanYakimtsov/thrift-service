package by.bsuir.iit.aipos.view;

import by.bsuir.iit.aipos.controller.ConnectionController;
import by.bsuir.iit.aipos.controller.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientWindow extends Application {

    private Stage primaryStage;
    private Scene primaryScene;

    private Stage connectionStage = new Stage();
    private Scene connectionScene;

    private MainController mainMainController;
    private ConnectionController connectionController;

    private ObservableList<String> observableNameList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadMainWindow();
        loadConnectDialog();
        showMainWindow();
    }

    private void loadMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/client.fxml"));
        primaryScene = new Scene(loader.load(), 1240, 600);
        mainMainController = loader.getController();
        mainMainController.setClientWindow(this);
        mainMainController.setPrimaryStage(primaryStage);
    }

    private void loadConnectDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/connectDialog.fxml"));
        connectionScene = new Scene(loader.load(), 250, 100);
        setConnectionStageParameters();
        connectionController = loader.getController();
    }

    private void setConnectionStageParameters() {
        connectionStage.initModality(Modality.WINDOW_MODAL);
        connectionStage.initOwner(primaryStage);
        connectionStage.setScene(connectionScene);
    }

    private void showMainWindow() throws IOException {
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public boolean showConnectionDialog(String host, String port) {
        connectionController.setClientWindow(this);
        connectionController.setConnectionStage(connectionStage);
        connectionController.setParameters(host, port);
        connectionStage.showAndWait();
        return connectionController.isOkClicked();
    }

    public ObservableList<String> getObservableNameList() {
        return observableNameList;
    }
}
