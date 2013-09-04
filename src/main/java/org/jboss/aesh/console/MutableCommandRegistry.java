package org.jboss.aesh.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class MutableCommandRegistry implements CommandRegistry {

    private Map<String,CommandContainer> registry = new HashMap<String, CommandContainer>();

    @Override
    public CommandContainer getCommand(String name, String completeLine) throws CommandNotFoundException {
        if(registry.containsKey(name))
            return registry.get(name);
        else
            throw new CommandNotFoundException("Command: "+name+" was not found.");
    }

    @Override
    public Set<String> getAllCommandNames() {
        return registry.keySet();
    }

    public void addCommand(CommandContainer container) {
        putIntoRegistry(container);
    }

    public void addCommand(Command command) {
        putIntoRegistry(new CommandContainer(command));
    }

    public void addCommand(Class<? extends Command> command) {
        putIntoRegistry(new CommandContainer(command));
    }

    private void putIntoRegistry(CommandContainer commandContainer) {
        if(!commandContainer.hasError() &&
                !registry.containsKey(commandContainer.getParser().getCommand().getName()))
            registry.put(commandContainer.getParser().getCommand().getName(), commandContainer);
    }

    public void removeCommand(String name) {
        if(registry.containsKey(name))
            registry.remove(name);
    }

}
