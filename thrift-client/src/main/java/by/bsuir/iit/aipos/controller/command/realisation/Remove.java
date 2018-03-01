package by.bsuir.iit.aipos.controller.command.realisation;

import by.bsuir.iit.aipos.controller.command.ICommand;
import javafx.scene.control.TextField;

public class Remove implements ICommand {

    private TextField nameField;

    public Remove(TextField nameField) {
        this.nameField = nameField;
    }

    @Override
    public void execute() {

    }
}
