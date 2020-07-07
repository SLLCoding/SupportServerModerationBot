package org.golde.discordbot.supportserver.command.guildmod;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.golde.discordbot.supportserver.constants.SSEmojis;
import org.golde.discordbot.supportserver.database.Database;
import org.golde.discordbot.supportserver.util.ModLog;
import org.golde.discordbot.supportserver.util.ModLog.ModAction;

import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandBan extends GuildModCommand {

	public static final int DEL_DAYS = 7;

	public CommandBan() {
		super("ban", "<player> [reason]", "ban a player", "b");
	}

	@Override
	protected void execute(CommandEvent event, List<String> args) {

		TextChannel tc = event.getTextChannel();
		Member member = event.getMember();

		if(event.getArgs().isEmpty())
		{
			replyError(tc, "Please provide the name of a player to ban!");
			return;
		}
		else {

			Member target = getMember(event, args, 1);

			if (args.isEmpty() || target == null) {
				replyError(tc, "Missing or invalid arguments");
				return;
			}


			String reason = String.join(" ", args.subList(2, args.size()));

			if (!event.getMember().canInteract(target) || target.getUser().isBot() || target.getUser().isFake()) {
				replyError(tc, SSEmojis.HAL9000 + " I'm sorry " + event.getMember().getAsMention() + ", I'm afraid I can't let you do that." );
				return;
			}


			if(reason == null || reason.isEmpty()) {
				reason = "No reason provided.";
			}

			final String reasonFinal = reason;

			Database.addOffence(target.getIdLong(), event.getAuthor().getIdLong(), ModAction.BAN, reason);

			MessageEmbed actionEmbed = ModLog.getActionTakenEmbed(
					ModAction.BAN, 
					event.getAuthor(), 
					new String[][] {
						new String[] {"Offender: ", "<@" + target.getId() + ">"}, 
						new String[] {"Reason:", StringUtils.abbreviate(reason, 250)}
					}
					);

			ModLog.log(event.getGuild(), actionEmbed);

			target.getUser().openPrivateChannel().queue((dmChannel) ->
			{
				dmChannel.sendMessage(actionEmbed).queue((unused1) ->
				{
					event.getGuild().ban(target, DEL_DAYS, String.format("Banned by: %#s, with reason: %s",
							event.getAuthor(), reasonFinal)).queue();
				});

			});

			replySuccess(tc, "Successfully banned " + target.getAsMention() + " for '**" + StringUtils.abbreviate(reason, 250) + "**'!");


		}
	}

}
