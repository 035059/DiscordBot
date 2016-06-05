package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Allin on 5/11/2016.
 */
public final class Google implements ICommand {

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "google";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "Posts a google search to the chat";
    }

    /**
     * Sends a message to the chat containing a google search of the inputted argument
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        if (Objects.equals(args[0], "LUCKY")) { // If the first argument is LUCKY
            try {
                args[0] = ""; // Set args[0] to an empty string
                // Post a new message the the chat the trigger message came from with a link to the top google search result for the rest of the arguments
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(String.valueOf(new URL("http://www.google.com/search?q=" + Arrays.toString(args).replaceAll("\\[|\\]|,", "").replaceAll(" ", "+").substring(1) + "&btnI"))).build();
            } catch (MalformedURLException | DiscordException | MissingPermissionsException | HTTP429Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                // Post a new message the the chat the trigger message came from with a link to the google search results for the rest of the arguments
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(String.valueOf(new URL("http://www.google.com/search?q=" + Arrays.toString(args).replaceAll("\\[|\\]|,", "").replaceAll(" ", "+")))).build();
            } catch (MalformedURLException | DiscordException | MissingPermissionsException | HTTP429Exception e) {
                e.printStackTrace();
            }
        }
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