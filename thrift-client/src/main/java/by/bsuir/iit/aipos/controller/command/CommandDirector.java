package by.bsuir.iit.aipos.controller.command;

import by.bsuir.iit.aipos.controller.Command;
import by.bsuir.iit.aipos.controller.MainController;
import by.bsuir.iit.aipos.controller.command.realisation.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {

    private Map<Command, ICommand> commandMap = new HashMap<>();

    public CommandDirector(MainController controller) {

        commandMap.put(Command.CONNECT, new Connect(controller.getClientWindow(), controller.getPatternsTable()));
        commandMap.put(Command.DISCONNECT, new Disconnect(controller.getPatternsTable()));
        commandMap.put(Command.ADD, new Add(controller.getName(), controller.getBody(), controller.getImage()));
        commandMap.put(Command.GET, new Get(controller.getName(), controller.getBody(), controller.getImage()));
        commandMap.put(Command.REMOVE, new Remove(controller.getName()));
        commandMap.put(Command.UPDATE , new Update(controller.getName(), controller.getBody(), controller.getImage()));
        commandMap.put(Command.BROWSE, new Browse(controller.getClientWindow(), controller.getImage(), controller.getPath()));
    }

    public ICommand getCommand(Command commandName) {
        return commandMap.get(commandName);
    }

}
