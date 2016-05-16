package main.java;

import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Allin on 5/11/2016.
 */
class EventHandler
{
    FileWriter fw = new FileWriter(new File("log.txt"));

    EventHandler() throws IOException {}

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        System.out.println("main.EventHandler is now ready");
    }

    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent event) throws IOException {
        log(event.getMessage().getAuthor().getName() + " on " + event.getMessage().getChannel().getName() + ": " + event.getMessage().getContent());
    }
    private void log(String archive) throws IOException {
        fw.append(archive).append("\n");
        fw.flush();
    }
}