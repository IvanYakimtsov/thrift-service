package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Browse implements ICommand {

    private Stage primaryStage;
    private ImageView imageView;
    private TextField pathField;

    public Browse(Stage primaryStage, ImageView imageView, TextField pathField) {
        this.primaryStage = primaryStage;
        this.imageView = imageView;
        this.pathField = pathField;
    }

    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        File imageFile = fileChooser.showOpenDialog(primaryStage);
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
