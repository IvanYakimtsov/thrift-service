package by.bsuir.iit.aipos.view;

import by.bsuir.iit.aipos.controller.ConnectionController;
import by.bsuir.iit.aipos.controller.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientWindow extends Application {

    private Stage primaryStage;
    private Scene primaryScene;

    private Stage connectionStage = new Stage();
    private Scene connectionScene;

    private ImageChooser imageChooser = new ImageChooser();

    private Alert infoDialog = new Alert(Alert.AlertType.INFORMATION);
    private Alert warningDialog = new Alert(Alert.AlertType.WARNING);

    private MainController mainController;
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
        primaryScene = new Scene(loader.load(), 600, 400);
        mainController = loader.getController();
        mainController.setClientWindow(this);
        mainController.setPrimaryStage(primaryStage);
        imageChooser.setPrimaryStage(primaryStage);
    }

    private void loadConnectDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/connect-dialog.fxml"));
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

    public Image showImageChooser(TextField pathFiled) throws IOException {
        return imageChooser.openImage(pathFiled);
    }

    public boolean showConnectionDialog(StringBuilder host, StringBuilder port) {
        connectionController.setClientWindow(this);
        connectionController.setConnectionStage(connectionStage);
        connectionController.setParameters(host, port);
        connectionStage.showAndWait();
        return connectionController.isOkClicked();
    }

    public void showInfoDialog(String title, String content) {
        infoDialog.setTitle(title);
        infoDialog.setHeaderText(null);
        infoDialog.setContentText(content);
        infoDialog.showAndWait();
    }

    public void showWarningDialog(String title, String content) {
        warningDialog.setTitle(title);
        warningDialog.setHeaderText(null);
        warningDialog.setContentText(content);
        warningDialog.showAndWait();
    }

    public ObservableList<String> getObservableNameList() {
        return observableNameList;
    }
}
