package main.java.commands;

import main.java.ICommand;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;


/**
 * Created by Allin on 5/11/2016.
 */
public final class Music implements ICommand {

    Music(){}

    @Override
    public void handle(IChannel channel, String[] args) {

    }

    @Override
    public String getName() {
        return "music";
    }

    @Override
    public void handle(IMessage message, String[] args) {

    }
}
