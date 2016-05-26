package main.java;

import sx.blah.discord.Discord4J;

import java.util.HashMap;

public class CommandRegistry {
	private static HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	public static HashMap<String, ICommand> getCommands() {
		return commands;
	}

	public static ICommand getCommandByName(String name) {
		return commands.get(name.toLowerCase());
	}

	public static ICommand getCommandFromAlias(String alias) {
		for (ICommand command : getCommands().values()) {
			if (command.getName().equalsIgnoreCase(alias)) {
				return command;
			} else {
				for (String commandAlias : command.getAliases()) {
					if (commandAlias.equalsIgnoreCase(alias)) {
						return command;
					}
				}
			}
		}
		return null;
	}

	public static void registerCommand(ICommand command) {
		if (!commands.containsValue(command)) {
			for (ICommand command1 : getCommands().values()) {
				for (String alias : command1.getAliases()) {
					if (alias.equalsIgnoreCase(command.getName())) {
						Discord4J.LOGGER.warn("Command \"" + command.getName() + "\" conflicts with alias in command \"" + command1.getName() + "\" Not Registering.");
						return;
					}
				}

				for (String alias : command.getAliases()) {
					if (alias.equalsIgnoreCase(command1.getName())) {
						Discord4J.LOGGER.warn("Alias in command \"" + command.getName() + "\" conflicts with command \"" + command1.getName() + "\" Not Registering.");
						return;
					}
				}

				for (String alias : command1.getAliases()) {
					for (String alis1 : command.getAliases()) {
						if (alias.equalsIgnoreCase(alis1)) {
							Discord4J.LOGGER.warn("Alias in command \"" + command.getName() + "\" conflicts with alias in command \"" + command1.getName() + "\" Not Registering.");
							return;
						}
					}
				}
			}
			commands.put(command.getName().toLowerCase(), command);
		} else {
			throw new RuntimeException("Attempt to register already-registered command.");
		}
	}
}
