package main.java;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class CommandModule implements IModule {

    /**
     * @param client the currently connected client
     * @return true, when a new CommandListener instance had been registered as a listener
     */
    @Override
    public boolean enable(IDiscordClient client) {
        client.getDispatcher().registerListener(new CommandListener());
        return true;
    }

    /**
     * @
     */
    @Override
    public void disable() {
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "Command API Module";
    }

    /**
     * @return
     */
    @Override
    public String getVersion() {
        return "1.0";
    }

    /**
     * @return
     */
    @Override
    public String getAuthor() {
        return "Allin Demopolis";
    }

    /**
     * @return
     */
    @Override
    public String getMinimumDiscord4JVersion() {
        return "2.3.0";
    }
}
