package org.golde.discordbot.utilities.event;

import org.golde.discordbot.shared.ESSBot;
import org.golde.discordbot.shared.constants.Roles;
import org.golde.discordbot.shared.event.EventBase;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;

public class PlayerCounter extends EventBase {
	
	public PlayerCounter(ESSBot bot) {
		super(bot);
	}

	
	@Override
	public void onReady(ReadyEvent event) {
		updateChannel(event.getJDA().getGuildById("594335572173258752"));
	}
	
	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		updateChannel(event.getGuild());
	}
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		updateChannel(event.getGuild());
	}
	
	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		Role the100club = event.getGuild().getRoleById(735287621974097950L);
		
		if(event.getRoles().contains(the100club)) {
			updateChannel(event.getGuild());
		}
	}
	
	private void updateChannel(Guild g) {
		VoiceChannel vc = g.getVoiceChannelById("661108732838674445");
		//int[] rawData = getMemberTotalCount(g);
		
		//vc.getManager().setName("Online: " + rawData[1] + " / " + rawData[0]).queue();
		vc.getManager().setName("Total Members: " + getTotalMemberCount(g)).queue();
	}
	
//	private int[] getMemberTotalCount(Guild g) {
//		int am[] = {0, 0};
//		for(Member m : g.getMemberCache().asSet()) {
//			
//			if(!m.getUser().isBot()) {
//				am[0]++;
//				if(m.getOnlineStatus() != OnlineStatus.OFFLINE && m.getOnlineStatus() != OnlineStatus.INVISIBLE && m.getOnlineStatus() != OnlineStatus.UNKNOWN) {
//					am[1]++;
//				}
//			}
//		}
//		return am;
//	}
	
	private int getTotalMemberCount(Guild g) {
		int toReturn = 0;
		
		for(Member m : g.getMembersWithRoles(g.getRoleById(Roles.MEMBER))) {
			if(!m.getUser().isBot() && !m.getUser().isFake()) {
				toReturn++;
			}
		}
		return toReturn;
	}
	
}
