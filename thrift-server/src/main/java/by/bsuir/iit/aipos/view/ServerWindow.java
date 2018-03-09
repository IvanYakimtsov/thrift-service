package by.bsuir.iit.aipos.view;

import by.bsuir.iit.aipos.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerWindow extends Application{

    private Stage primaryStage;
    private Scene primaryScene;

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
}
