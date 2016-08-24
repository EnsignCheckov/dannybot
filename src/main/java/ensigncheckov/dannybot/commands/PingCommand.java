package ensigncheckov.dannybot.commands;

import ensigncheckov.dannybot.Command;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class PingCommand implements Command {

	public final String HELP = "USAGE: !ping";
	
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		event.getTextChannel().sendMessage("PONG");
	}

	public String help() {
		return HELP;
	}

	public void execute(boolean success, MessageReceivedEvent event) {
		return;
	}	
}
