package main.java.commands;

import com.github.kaioru.instructability.Defaults;
import com.github.kaioru.instructability.discord4j.Discord4JCommand;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

import java.util.LinkedList;

/**
 * Created by Allin on 5/11/2016.
 */
public class Delete extends Discord4JCommand{
    @Override
    public String getName() {
        return "Delete";
    }

    @Override
    public String getDesc() {
        return Defaults.DESCRIPTION;
    }

    @Override
    public void execute(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
        event.getMessage();
    }
}
