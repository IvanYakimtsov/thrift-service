package by.bsuir.iit.aipos.view;

import by.bsuir.iit.aipos.controller.ConnectionController;
import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.thrift.Header;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ClientWindow extends Application {

    private Stage primaryStage;
    private Scene primaryScene;

    private Stage connectionStage = new Stage();
    private Scene connectionScene;

    private ImageChooser imageChooser = new ImageChooser();

    private Alert infoDialog = new Alert(Alert.AlertType.INFORMATION);
    private Alert warningDialog = new Alert(Alert.AlertType.WARNING);
    private TextInputDialog inputDialog = new TextInputDialog();

    private MainController mainController;
    private ConnectionController connectionController;

    private ObservableList<Header> observableNameList = FXCollections.observableArrayList();

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
        loader.setLocation(getClass().getResource(ViewConfig.MAIN_WINDOW_FXML));
        primaryScene = new Scene(loader.load(), ViewConfig.MAIN_WINDOW_WIDTH, ViewConfig.MAIN_WINDOW_HEIGHT);
        mainController = loader.getController();
        mainController.setClientWindow(this);
        mainController.setPrimaryStage(primaryStage);
        imageChooser.setPrimaryStage(primaryStage);
    }

    private void loadConnectDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewConfig.CONNECT_DIALOG_FXML));
        connectionScene = new Scene(loader.load(), ViewConfig.CONNECT_DIALOG_WIDTH, ViewConfig.CONNECT_DIALOG_HEIGHT);
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

    public boolean showInputDialog(String title, String content, StringBuilder authorEmail) {
        inputDialog.setTitle(title);
        inputDialog.setHeaderText(null);
        inputDialog.setContentText(content);
        return handleInput(authorEmail);

    }

    private boolean handleInput(StringBuilder authorEmail) {
        Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            authorEmail.append(result.get());
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<Header> getObservableNameList() {
        return observableNameList;
    }
}
