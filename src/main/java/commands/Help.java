package main.java.commands;

import main.java.CommandRegistry;
import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Allin on 5/26/2016.
 */
public final class Help implements ICommand {
    /**
     * Get the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * Get the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "posts a message with help for each function";
    }

    /**
     * Sends a message to the chat with a list of commands
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        StringBuilder stringBuilder = new StringBuilder(); // MAke a new StringBuilder
        stringBuilder.append("**List of commands:**\n"); // Append a title
        for (ICommand s : CommandRegistry.getCommands().values()) { // For each command in the registry
            stringBuilder.append("`").append(s.getName()).append("`: ").append(s.getRole()); // Append the command info
            stringBuilder.append("\n"); // Append a line break
        }
        try {
            // Send a message to the same chat as the trigger message with the String Builder as contents
            new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(stringBuilder.toString()).build();
        } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the name and role of the command
     * @return the name and role of the command
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
