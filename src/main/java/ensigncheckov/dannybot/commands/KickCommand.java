package ensigncheckov.dannybot.commands;


import java.util.List;
import ensigncheckov.dannybot.Command;
import net.dv8tion.jda.utils.InviteUtil;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class KickCommand implements Command {

	public final String HELP = "USAGE: !kick USER";
	
	/*
	 * TO DO:
	 * -- If anyone has a web address as their name, 
	 * -- the bot cannot kick anyone that joined after that user.
	 * -- PROBLEM: the getUsers() stops fetching users after reading 
	 * -- the https MAYBE the semi colon after https is canceling the getter
	 * ++ Maybe do an .contains https, in which case find that id and kick 
	 * ++ that user too the run the command again, finding the id will be hard
	 */
	
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true; 
	}

	public void action(String[] args, MessageReceivedEvent event) {
		if (args.length == 1) {
			String[] authoridrole = event.getGuild().getRolesForUser(event.getAuthor()).toString().split(":");
			String[] authorrole = authoridrole;
			authorrole[0] = "noob";
			if (authoridrole.length > 1) {
				authorrole = authoridrole[1].split("\\(");
			}			
			if(authorrole[0].equalsIgnoreCase("meta") || authorrole[0].equalsIgnoreCase("rex")) { //Author is an admin or EnsignCheckov
				List<User> users = event.getGuild().getUsers();
				for (int i = 0; users.size() > i; i = i + 1){
					if (args[0].equalsIgnoreCase(users.get(i).getUsername())) {
						event.getTextChannel().sendMessage(users.get(i).getUsername() + " has been kicked.");
						event.getTextChannel().sendMessage("Reason: racism is bad");					
						InviteUtil.Invite invite = InviteUtil.createInvite(event.getTextChannel(), InviteUtil.InviteDuration.INFINITE, 1, false);
						users.get(i).getPrivateChannel().sendMessage("https://discord.gg/" + invite.getCode());
						event.getTextChannel().getGuild().getManager().kick(users.get(i).getId());
						break;
					}
				}
			}
			else {
				event.getTextChannel().sendMessage("Only administrators can run this command... for obvious reasons.");
			}
		}
		else {
			event.getTextChannel().sendMessage(help());
		}
	}

	public String help() {
		return HELP;
	}

	public void execute(boolean success, MessageReceivedEvent event) {
		return;
	}	
}
