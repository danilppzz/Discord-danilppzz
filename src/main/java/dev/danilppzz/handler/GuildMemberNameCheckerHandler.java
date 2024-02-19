package dev.danilppzz.handler;

import dev.danilppzz.Main;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberNameCheckerHandler extends ListenerAdapter {

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        String oldNickName = event.getOldNickname();
        String newNickName = event.getNewNickname();
        String userID = event.getMember().getId();
        Main.logger.info("User " + userID + " changed nickname from " + oldNickName + " to " + newNickName);
    }
}

