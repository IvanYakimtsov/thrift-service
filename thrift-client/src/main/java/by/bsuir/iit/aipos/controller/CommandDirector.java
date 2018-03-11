package by.bsuir.iit.aipos.controller;

import by.bsuir.iit.aipos.controller.command.*;
import by.bsuir.iit.aipos.controller.command.strategy.AddNewArticle;
import by.bsuir.iit.aipos.controller.command.strategy.UpdateOldArticle;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {

    private Map<Command, ICommand> commandMap = new HashMap<>();

    public CommandDirector(MainController controller) {

        commandMap.put(Command.CONNECT, new Connect(controller));
        commandMap.put(Command.DISCONNECT, new Disconnect(controller));
        commandMap.put(Command.ADD, new AddData(controller, new AddNewArticle(controller)));
        commandMap.put(Command.GET, new Get(controller));
        commandMap.put(Command.REMOVE, new Remove(controller));
        commandMap.put(Command.UPDATE, new AddData(controller, new UpdateOldArticle(controller)));
        commandMap.put(Command.UPDATE_TABLE, new UpdateTable(controller));
        commandMap.put(Command.BROWSE, new Browse(controller));
    }

    public ICommand getCommand(Command commandName) {
        return commandMap.get(commandName);
    }
}
