import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * Created by Allin on 5/11/2016.
 */
public class ReadyEventListener implements IListener<ReadyEvent>
{
    public void handle(ReadyEvent event)
    {
        System.out.println("ReadyEventListener is now ready");
        //runCommand();
    }
}
