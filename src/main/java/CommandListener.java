package main.java;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class CommandListener implements IListener<MessageReceivedEvent> {
	public static String commandDiscriminator = "-";

	@Override
	public void handle(MessageReceivedEvent event) {
		if (event.getMessage().getContent().startsWith(commandDiscriminator)) {
			String commandName = event.getMessage().getContent().substring(1);
			if (commandName.contains(" ")) {
				commandName = commandName.substring(0, commandName.indexOf(" "));
			}
			ICommand command = CommandRegistry.getCommandFromAlias(commandName);
			if (command != null) {
				String[] arguments = new String[] {};
				if (event.getMessage().getContent().contains(" ")) {
					arguments = event.getMessage().getContent().substring(event.getMessage().getContent().indexOf(" ") + 1).split("\\s+");
				}
				if (command.deletesMessage()) {
					try {
						event.getMessage().delete();
					} catch ( HTTP429Exception | DiscordException | MissingPermissionsException e) {
						e.printStackTrace();
					}
				}
				Discord4J.LOGGER.info("Command " + commandName + " executed.");
				command.handle(event.getMessage(), arguments);
			} else {
				Discord4J.LOGGER.warn("Command " + commandName + " not found.");
			}
		}
	}
}
