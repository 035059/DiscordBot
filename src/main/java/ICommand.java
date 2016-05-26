package main.java;

import sx.blah.discord.handle.obj.IMessage;

public interface ICommand {

	String getName();

	void handle(IMessage message, String[] args);

	default String[] getAliases() {
		return new String[0];
	}

	default boolean deletesMessage() { return false; }
}
