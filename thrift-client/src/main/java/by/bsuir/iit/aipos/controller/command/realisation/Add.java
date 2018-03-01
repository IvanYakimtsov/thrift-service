package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Add implements ICommand {

    private TextField nameField;
    private TextArea bodyField;
    private ImageView imageView;

    public Add(TextField nameField, TextArea bodyField, ImageView imageView) {
        this.nameField = nameField;
        this.bodyField = bodyField;
        this.imageView = imageView;
    }

    @Override
    public void execute() {

    }
}
