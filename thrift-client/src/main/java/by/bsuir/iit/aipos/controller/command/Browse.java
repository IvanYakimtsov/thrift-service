package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Browse implements ICommand {

    private ClientWindow clientWindow;
    private ImageView patternImage;
    private TextField formatField;

    public Browse(MainController mainController) {
        this.clientWindow = mainController.getClientWindow();
        this.patternImage = mainController.getImage();
        this.formatField = mainController.getFormatField();
    }

    @Override
    public void execute() {
        try {
            Image image = clientWindow.showImageChooser(formatField);
            if (image != null) {
                patternImage.setImage(image);
            }
        } catch (IOException e) {
            clientWindow.showWarningDialog("Browse warning", "Unable to open image!");
        }
    }
}
