package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import by.bsuir.iit.aipos.view.ClientWindow;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Browse implements ICommand {

    private ClientWindow clientWindow;
    private ImageView imageView;
    private TextField pathField;

    public Browse(ClientWindow clientWindow, ImageView imageView, TextField pathField) {
        this.clientWindow = clientWindow;
        this.imageView = imageView;
        this.pathField = pathField;
    }

    @Override
    public void execute() {
        try {
            Image image = clientWindow.showImageChooser(pathField);
            imageView.setImage(image);
        } catch (IOException e) {
            clientWindow.shoWarningDialog("Browse warning", "Unable to open image!");
        }
    }
}
