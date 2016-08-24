package ensigncheckov.dannybot;

import java.util.HashMap;
import ensigncheckov.dannybot.commands.KickCommand;
import ensigncheckov.dannybot.commands.PingCommand;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.entities.User;

public class Main {

	private static JDA jda;
	public static final CommandParser parser = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
	public static void main(String[] args) {
		try{
			//https://discordapp.com/oauth2/authorize?&client_id=210421193138896896&scope=bot to add it to a server
			jda = new JDABuilder().addListener(new BotListener()).setBotToken("MjEwNDIxMTkzMTM4ODk2ODk2.CpRJIQ.uGhkZmW9PfmBbWIz71mZuuLmOww").buildBlocking();
			jda.setAutoReconnect(true);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		commands.put("ping", new PingCommand());
		commands.put("kick", new KickCommand());
	}

	public static void handleCommand(CommandParser.CommandContainer cmd) {
		if(commands.containsKey(cmd.invoke)) {
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
			
			if(safe){
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).execute(safe, cmd.event);
			} else {
				commands.get(cmd.invoke).execute(safe, cmd.event);
			}
		}
	}
	public User getUserWithID(String id) {
		return jda.getUserById(id);
	}	
}
