package main.java.commands;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.exception.GiphyException;
import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Allin on 5/11/2016.
 */
public final class Meme implements ICommand {
    Giphy giphy = new Giphy("dc6zaTOxFJmzC");

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "meme";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "Posts a gif from Giphy";
    }

    /**
     * Posts a Gif from Giphy tagged with the passed args
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        try {
            // Post a Gif from Giphy tagged with the passed args
            new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(giphy.searchRandom(String.join(" ", (CharSequence[]) args)).getData().getImageOriginalUrl()).build();
        } catch (GiphyException | DiscordException | MissingPermissionsException | HTTP429Exception e) { // If no gif is found, or some other error occurs
            try {
                // Send a message to the use telling them
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent("No gif found").build();
            } catch (HTTP429Exception | DiscordException | MissingPermissionsException e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     * Gets whether or not the triggering message is deleted
     * @return whether or not the triggering message is deleted
     */
    @Override
    public boolean deletesMessage() {
        return true;
    }

    /**
     * Gets the name and role of the command
     * @return the name and role of the command
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
