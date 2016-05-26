package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Allin on 5/11/2016.
 */
public class Delete implements ICommand {
    @Override
    public void handle(IChannel channel, String[] args) {
        try {
            new MessageBuilder(GeneralCommands.client).withChannel(channel).withContent("༼ つ ◕\\_◕ ༽つHYPE༼ つ ◕\\_◕ ༽つ").build();
        } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void handle(IMessage message, String[] args) {

    }
}
