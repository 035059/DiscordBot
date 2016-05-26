package main.java.commands;

/**
 * Created by Allin on 5/25/2016.
 */

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.Discord4J;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

public class Hype implements ICommand {
    @Override
    public void handle(IChannel channel, String[] args) {
        try {
            new MessageBuilder(GeneralCommands.client).withChannel(channel).withContent("༼ つ ◕\\_◕ ༽つHYPE༼ つ ◕\\_◕ ༽つ").build();
        } catch (HTTP429Exception | MissingPermissionsException | DiscordException e) {
            Discord4J.LOGGER.error("Exception", e);
        }
    }

    @Override
    public String getName() {
        return "hype";
    }

    @Override
    public void handle(IMessage message, String[] args) {

    }
}
