package main.java.commands;

import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Allin on 5/12/2016.
 */
public final class Message implements ICommand {

    @Override
    public String getName() {
        return "message";
    }

    @Override
    public void handle(IMessage message, String[] args) {

    }
}
