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

    @Override
    public String getName() {
        return "google";
    }

    @Override
    public String getRole() {
        return "Posts a google search to the chat";
    }

    @Override
    public void handle(IMessage message, String[] args) {
        if (Objects.equals(args[0], "LUCKY")) {
            try {
                args[0] = "";
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(String.valueOf(new URL("http://www.google.com/search?q=" + Arrays.toString(args).replaceAll("\\[|\\]|,", "").replaceAll(" ", "+").substring(1) + "&btnI"))).build();
            } catch (MalformedURLException | DiscordException | MissingPermissionsException | HTTP429Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(String.valueOf(new URL("http://www.google.com/search?q=" + Arrays.toString(args).replaceAll("\\[|\\]|,", "").replaceAll(" ", "+")))).build();
            } catch (MalformedURLException | DiscordException | MissingPermissionsException | HTTP429Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}