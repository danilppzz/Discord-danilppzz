package dev.soizx.handler;

import dev.soizx.Main;
import dev.soizx.util._Date;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberSendMessageHandler extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {


        Main.logger.info("Joined - "+event.getMember().getEffectiveName() + " - newValue ("+event.getGuild().getMemberCount()+")");
    }
}
