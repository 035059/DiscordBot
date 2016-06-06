package main.java.commands;

import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Allin on 5/11/2016.
 */
public final class WolframAlpha implements ICommand {

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "wolframalpha";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return null;
    }


    /**
     * Sends a query to wolframalpha, and posts the response
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {

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
