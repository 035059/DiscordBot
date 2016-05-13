import sx.blah.discord.api.Event;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**)
 * Created by Allin on 5/12/2016.
 */

class CommandExecutionEvent extends Event {

    private final IMessage message;
    private final String command;
    private final IUser by;
    private final String[] args;

    CommandExecutionEvent(IMessage message, String command, IUser by, String[] args) {
        this.message = message;
        this.command = command;
        this.by = by;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public IMessage getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }

    public IUser getBy() {
        return by;
    }
}