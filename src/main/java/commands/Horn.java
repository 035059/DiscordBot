package main.java.commands;

import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Allin on 5/11/2016.
 */
public final class Horn implements ICommand {


    @Override
    public String getName() {
        return "horn";
    }

    @Override
    public void handle(IMessage message, String[] args) {

    }
}
