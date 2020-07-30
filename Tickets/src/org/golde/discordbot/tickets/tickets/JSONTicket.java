package org.golde.discordbot.tickets.tickets;

import java.util.List;

import org.golde.discordbot.shared.ESSBot;
import org.golde.discordbot.supportserver.Main;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;

@Getter
public class JSONTicket {

	final List<JSONMessage> messages;
	final long owner;
	final long channel;
	final List<Long> members;
	final String channelName;
	final boolean isPrivate;
	
	public JSONTicket(Ticket ticket) {
		messages = ticket.messages;
		owner = ticket.owner.getIdLong();
		channel = ticket.channel.getIdLong();
		this.members = ticket.members;
		this.channelName = ticket.channel.getName();
		this.isPrivate = ticket.isPrivate;
	}
	
	public Ticket toTicket(ESSBot bot, String fileName) {
		Guild g = bot.getGuild();
		Ticket ticket = new Ticket();
		ticket.channel = g.getTextChannelById(channel);
		ticket.owner = g.getMemberById(owner);
		ticket.members = members;
		ticket.messages = messages;
		ticket.fileName = "tickets/" + fileName.replace(".json", "");
		ticket.isPrivate = isPrivate;
		return ticket;
	}
	
}