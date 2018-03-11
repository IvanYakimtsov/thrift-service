package by.bsuir.iit.aipos.view;

import by.bsuir.iit.aipos.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerWindow extends Application{

    private Stage primaryStage;
    private Scene primaryScene;

    private Alert infoDialog = new Alert(Alert.AlertType.INFORMATION);
    private Alert warningDialog = new Alert(Alert.AlertType.WARNING);

    private MainController mainController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadMainWindow();
        showMainWindow();
    }

    private void loadMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/server.fxml"));
        primaryScene = new Scene(loader.load(), 600, 400);
        mainController = loader.getController();
        mainController.setServerWindow(this);
        mainController.setPrimaryStage(primaryStage);
    }

    private void showMainWindow() {
        primaryStage.setScene(primaryScene);
        primaryStage.show();
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
}
