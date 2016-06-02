package main.java;

import sx.blah.discord.handle.obj.IMessage;

public interface ICommand {

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    String getName();

    /**
     * Gets the functionality of the command
     * @return the role of the command
     */
    String getRole();

    /**
     * the functional part of the command
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    void handle(IMessage message, String[] args);

    /**
     * Gets the other possible names for the command
     * @return empty String array, length 0
     */
    default String[] getAliases() {
        return new String[0];
    }

    /**
     * Boolean value tells the commandListener whether or not to delete the message that triggered the command
     * @return true if the message should be deleted
     */
    default boolean deletesMessage() {
        return false;
    }
}
