package main.java.commands;

/**
 * Created by Allin on 5/25/2016.
 */

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.Discord4J;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

public class Hype implements ICommand {

    /***
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "hype";
    }

    /***
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "For when the train simply cannot be stopped";
    }

    /**\
     * Gets whether or not the triggering message is deleted
     * @return whether or not the triggering message is deleted
     */
    @Override
    public boolean deletesMessage() {
        return true;
    }

    /**
     * Posts a hype message to the triggering messages channel
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        try {
            // Post a new message with HYPE!!!!!
            new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent("༼ つ ◕\\_◕ ༽つHYPE༼ つ ◕\\_◕ ༽つ").build();
        } catch (HTTP429Exception | MissingPermissionsException | DiscordException e) {
            Discord4J.LOGGER.error("Exception", e);
        }
    }

    /***
     * Gets the name and role of the command
     * @return the name and role of the command
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
