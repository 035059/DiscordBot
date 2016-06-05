package main.java;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class CommandModule implements IModule {

    /**
     * Enables the module
     * @param client the currently connected client
     * @return true, when a new CommandListener instance had been registered as a listener
     */
    @Override
    public boolean enable(IDiscordClient client) {
        client.getDispatcher().registerListener(new CommandListener());
        return true;
    }

    /**
     * Disables the module
     */
    @Override
    public void disable() {
    }

    /**
     * Gets the name of the module
     * @return the name of the module
     */
    @Override
    public String getName() {
        return "Command API Module";
    }

    /**
     * Gets the version number
     * @return the versiuon number
     */
    @Override
    public String getVersion() {
        return "1.0";
    }

    /**
     * Gets the author's name
     * @return the author's name
     */
    @Override
    public String getAuthor() {
        return "Allin Demopolis";
    }

    /**
     * Gets the minimum Discord4j verizon this module can be used with
     * @return the minimum Discord4j verizon this module can be used with
     */
    @Override
    public String getMinimumDiscord4JVersion() {
        return "2.3.0";
    }
}
