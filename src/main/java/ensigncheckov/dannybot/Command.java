package ensigncheckov.dannybot;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public interface Command {
	
	public boolean called(String[] args, MessageReceivedEvent event);
	public void action(String[] args, MessageReceivedEvent event);
	public String help();
	public void execute(boolean success, MessageReceivedEvent event);
	
}
