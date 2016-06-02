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

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getRole() {
        return "Posts a gif from Giphy";
    }

    @Override
    public void handle(IMessage message, String[] args) {
        try {
            new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(giphy.searchRandom(String.join(" ", (CharSequence[]) args)).getData().getImageOriginalUrl()).build();
        } catch (GiphyException | DiscordException | MissingPermissionsException | HTTP429Exception e) {
            try {
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent("No gif found").build();
            } catch (HTTP429Exception | DiscordException | MissingPermissionsException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    public boolean deletesMessage() {
        return true;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
