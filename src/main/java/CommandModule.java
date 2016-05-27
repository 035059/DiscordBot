package main.java;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class CommandModule implements IModule {

	@Override
	public boolean enable(IDiscordClient client) {
		client.getDispatcher().registerListener(new CommandListener());
		return true;
	}

	@Override
	public void disable() {}

	@Override
	public String getName() {
		return "Command API Module";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "Allin Demopolis";
	}

	@Override
	public String getMinimumDiscord4JVersion() {
		return "2.3.0";
	}
}
