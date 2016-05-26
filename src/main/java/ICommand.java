package main.java;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public interface ICommand {

	void handle(IChannel channel, String[] args);

	String getName();

	void handle(IMessage message, String[] args);

	default String[] getAliases() {
		return new String[0];
	}

	default boolean deletesMessage() { return false; }
}
