package dev.soizx.handler;

import dev.soizx.Main;
import dev.soizx.util.Date;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberJoinHandler extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel textChannel  = event.getGuild().getTextChannelById("1158719887754141726");
        User user = event.getMember().getUser();

        if (textChannel != null) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            // EmbedBuilder Props
            embedBuilder.setColor(Color.decode("#E81F63"));
            embedBuilder.setThumbnail(user.getAvatarUrl());
            embedBuilder.setTitle("Welcome "+user.getName());
            embedBuilder.addField("Count","Now we are ` "+event.getGuild().getMemberCount()+" ` members in the server",false);
            embedBuilder.addField("Joined at ", Date.now(Date.allFormat),true);
            embedBuilder.addField("Created at ",""+user.getTimeCreated(),true);
            embedBuilder.addField("", user.getAsMention(), false);

            // SendMessage Embed
            textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
        }

        Main.logger.info("Joined - "+event.getMember().getEffectiveName() + " - newValue ("+event.getGuild().getMemberCount()+")");
    }
}
