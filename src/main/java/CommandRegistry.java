package main.java;

import sx.blah.discord.Discord4J;

import java.util.HashMap;

public class CommandRegistry {
    private static HashMap<String, ICommand> commands = new HashMap<>();

    public static HashMap<String, ICommand> getCommands() {
        return commands;
    }

    public static ICommand getCommandByName(String name) {
        return commands.get(name.toLowerCase());
    }

    /**
     * Returns a ICommand that has the passed alias
     * @param alias an alternate name for a command
     * @return the command that matches the alias
     */
    public static ICommand getCommandFromAlias(String alias) {
        for (ICommand command : getCommands().values()) { // For each command in the registry
            if (command.getName().equalsIgnoreCase(alias)) { // If the name of the command is the alias
                return command; // Return the command
            } else {
                for (String commandAlias : command.getAliases()) { // For each alias in the command
                    if (commandAlias.equalsIgnoreCase(alias)) { // If the alias matches the passed alias
                        return command; // Return the command
                    }
                }
            }
        }
        return null; // If no matching command is found, return null
    }

    /**
     * Adds a command to the registry
     * @param command the command to be added to the registry
     */
    public static void registerCommand(ICommand command) {
        if (!commands.containsValue(command)) { // If the registry doesn't have the passed command
            for (ICommand command1 : getCommands().values()) { // For each command in the registry
                for (String alias : command1.getAliases()) { // For each alias in the registry command
                    if (alias.equalsIgnoreCase(command.getName())) { // If the alias matches the name of the passed command
                        // Log an error
                        Discord4J.LOGGER.warn("Command \"" + command.getName() + "\" conflicts with alias in command \"" + command1.getName() + "\" Not Registering.");
                        return; // End the function
                    }
                }

                for (String alias : command.getAliases()) { //  For each alias in the passed command
                    if (alias.equalsIgnoreCase(command1.getName())) { // If the alias matches the registry command
                        // Log an error
                        Discord4J.LOGGER.warn("Alias in command \"" + command.getName() + "\" conflicts with command \"" + command1.getName() + "\" Not Registering.");
                        return; // Exit the function
                    }
                }

                for (String alias : command1.getAliases()) { // For each alias in the registry command
                    for (String alis1 : command.getAliases()) { // For each alias in the passed command
                        if (alias.equalsIgnoreCase(alis1)) { // If the aliases match
                            // Log an error
                            Discord4J.LOGGER.warn("Alias in command \"" + command.getName() + "\" conflicts with alias in command \"" + command1.getName() + "\" Not Registering.");
                            return; //  Exit the function
                        }
                    }
                }
            }
            commands.put(command.getName().toLowerCase(), command); // Add the command to the registry
        } else { // If the registry does have the passes command
            throw new RuntimeException("Attempt to register already-registered command."); // throw an error
        }
    }
}
