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
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public void handle(IMessage message, String[] args) {
        try {
            new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(
                    "Commands:\n" +
                            CommandRegistry.getCommands().values().toString()
            ).build();
        } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.getName() + ": " + this.getRole();
    }
}
