package org.golde.discordbot.shared.command.chatmod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.golde.discordbot.shared.ESSBot;
import org.golde.discordbot.shared.command.BaseCommand;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public abstract class ChatModCommand extends BaseCommand {

	private static final Category CATEGORY_CHAT_MODERATOR = new Category("\ud83d\udee0\ufe0f Chat Moderator");
	
	public ChatModCommand(@Nonnull ESSBot bot, @Nonnull String nameIn, @Nullable String argsIn, @Nullable String helpIn, @Nullable String... aliasesIn) {
		super(bot, nameIn, argsIn, helpIn, aliasesIn);
		this.category = CATEGORY_CHAT_MODERATOR;
		this.requiredRole = CATEGORY_CHAT_MODERATOR.getName();
	}
	
	public final static boolean isModerator(Member person) {
		for(Role r : person.getRoles()) {
			if(r.getName().equals(CATEGORY_CHAT_MODERATOR.getName())) {
				return true;
			}
		}
		return false;
	}
	
	
}