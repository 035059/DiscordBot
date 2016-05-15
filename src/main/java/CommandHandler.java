import commands.Commands;
import commands.Message;
import sx.blah.discord.api.EventSubscriber;

/**
 * Created by Allin on 5/12/2016.
 */
public class CommandHandler {

    @EventSubscriber
    public void onCommandEvent(CommandExecutionEvent event) {
        try {
            Commands.valueOf(event.getCommand()).runCommand();
        } catch (IllegalArgumentException ex) {
            ////TODO Send message to qwerier "incorrect message"
            Message.send(event.getClient(), event.getMessage().getChannel(), "@"+event.getBy().getName() + " " + event.getMessage().getContent() + "is not a command");
        }
    }
}
