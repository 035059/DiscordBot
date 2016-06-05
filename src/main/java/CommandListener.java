package main.java;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

class CommandListener implements IListener<MessageReceivedEvent> {

    /**
     * When an event is thrown, if it is a MessageReceivedEvent, it will trigger this method
     * @param event the thrown event
     */
    @Override
    public void handle(MessageReceivedEvent event) {
        String commandDiscriminator = "-";
        if (event.getMessage().getContent().startsWith(commandDiscriminator)) {// If the content of the message starts with the command discriminator
            String commandName = event.getMessage().getContent().substring(1); // Remove the first character

            if (commandName.contains(" ")) { // If there are spaces in the command
                commandName = commandName.substring(0, commandName.indexOf(" ")); // Strip the arguments
            }

            ICommand command = CommandRegistry.getCommandFromAlias(commandName); // Create a new command instance from the inputted value

            if (command != null) { // If the command exists
                String[] arguments = new String[]{};
                if (event.getMessage().getContent().contains(" ")) { // If the content of the messages contains spaces
                    arguments = event.getMessage().getContent().substring(event.getMessage().getContent().indexOf(" ") + 1).split("\\s+"); // Get the arguments from the command
                }
                if (command.deletesMessage()) { // If the command called's deletesMessage bool is true
                    try {
                        event.getMessage().delete(); // Delete the message that triggered the command
                    } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Command executed: " + command.getName()); // Print to the console the name of the deleted message
                command.handle(event.getMessage(), arguments); // Call the command, and pass the message that called it, and the stripped arguments
            } else { // If the command doesn't exist
                try {
                    new MessageBuilder(GeneralCommands.client).withChannel(event.getMessage().getChannel()).withContent("Command " + commandName + " not found.").build(); // Send a message to the channel to inform the user that it doesn't exist
                } catch (HTTP429Exception | MissingPermissionsException | DiscordException e) {
                    Discord4J.LOGGER.error("Command Not Found message error: ", e);
                }
            }
        }
    }
}