package org.golde.discordbot.shared.command.owner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.golde.discordbot.shared.ESSBot;

public abstract class OwnerCommandDangerous extends OwnerCommand {

	public OwnerCommandDangerous(@Nonnull ESSBot bot, @Nonnull String nameIn, @Nullable String argsIn, @Nullable String helpIn, @Nullable String... aliasesIn) {

		super(bot, nameIn, argsIn, helpIn, aliasesIn);

		this.ownerCommand = true; //Makes it so only from my user account, I can run the command. Disreguards any roles what so ever.
	}

}
