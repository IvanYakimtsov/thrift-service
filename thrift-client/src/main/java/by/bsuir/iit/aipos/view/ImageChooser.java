package by.bsuir.iit.aipos.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.codehaus.plexus.util.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageChooser {

    private final String[] formats = {"*.png", "*.gif", "*.jpg", "*.jpeg"};

    private Stage primaryStage;
    private final FileChooser fileChooser;
    private final List<ExtensionFilter> filters;

    public ImageChooser() {
        fileChooser = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter(String.join(",", formats), formats);
        filters = fileChooser.getExtensionFilters();
        filters.add(filter);
    }

    public Image openImage(TextField pathFiled) throws IOException {
        File image = fileChooser.showOpenDialog(primaryStage);
        if (image != null) {
            pathFiled.setText(FileUtils.getExtension(image.getName()));
            BufferedImage bufferedImage = ImageIO.read(image);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } else {
            return null;
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
