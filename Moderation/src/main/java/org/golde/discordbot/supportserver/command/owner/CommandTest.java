package org.golde.discordbot.supportserver.command.owner;

import java.util.List;

import javax.annotation.Nonnull;

import org.golde.discordbot.shared.ESSBot;
import org.golde.discordbot.shared.command.owner.OwnerCommandDangerous;
import org.golde.discordbot.shared.util.EnumReplyType;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.vdurmont.emoji.EmojiManager;

import net.dv8tion.jda.api.entities.TextChannel;

public class CommandTest extends OwnerCommandDangerous {

	@SuppressWarnings("unused")
	private final EventWaiter waiter;

	public CommandTest(@Nonnull ESSBot bot, EventWaiter waiter) {
		super(bot, "test", null, "Its for testing, thats all it does");
		this.waiter = waiter;
	}

	@Override
	protected void execute(CommandEvent event, List<String> args) {

		//Guild g = event.getGuild();

		TextChannel tc = event.getTextChannel();

		String breaker = "\n\n";

		String desc = "**Please select the option that best suits the issue your having**"
				+ breaker
				+ getEmo("one") + " MCP is not decompiling / I have errors in my command prompt window"
				+ breaker
				+ getEmo("two") + " I have errors in my code, and I can't figure out how to fix it"
				+ breaker
				+ getEmo("three") + " My client is crashing, and I can not figure out why"
				+ breaker
				+ getEmo("four") + " I am having an issue reguarding another member on this guild"
				+ breaker
				+ getEmo("question") + " **None of the above options answer my question**"

				;

		tc.sendMessage(this.getReplyEmbed(EnumReplyType.NONE, "Tickets", desc)).queue(success -> {

			success.addReaction(getEmo("one")).queue(onSuccess2 -> {
				success.addReaction(getEmo("two")).queue(onSuccess3 -> {
					success.addReaction(getEmo("three")).queue(onSuccess4 -> {
						success.addReaction(getEmo("four")).queue(onSuccess5 -> {
							//success.addReaction(getEmo("five")).queue(onSuccess6 -> {
								success.addReaction(getEmo("question")).queue(onSuccess7 -> {

								});
							//});
						});
					});
				});
			});

		});
		
//		ButtonMenu menu = new ButtonMenu.Builder()
//				.setEventWaiter(waiter)
//				.setDescription(desc)
//				.addChoices(getEmo("one"), getEmo("two"), getEmo("three"), getEmo("four"), getEmo("five"), getEmo("question"))
//				.setColor(new Color(Main.EMBED_COLOR))
//				.setAction(onReact -> {
//					
//					System.out.println(onReact.getName());
//					
//				})
//				.build();
//
//		menu.display(g.getTextChannelById(Channels.TICKETS));

	}
	
	String getEmo(String discord) {
		return EmojiManager.getForAlias(":" + discord + ":").getUnicode();
	}


}
