package dev.soizx.handler;

import dev.soizx.Main;
import dev.soizx.util.Validations;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberJoinHandler extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel textChannel = event.getGuild().getTextChannelById("1158719887754141726");
        User user = event.getMember().getUser();

        if (textChannel != null) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Welcome " + user.getName(), "https://discord.gg/7MePMjSnNr");
            embedBuilder.setDescription(user.getAsMention()+" has entered the server,\nnow we are "+event.getGuild().getMemberCount()+" users!");
            embedBuilder.setColor(Color.decode("#4F90DF"));
            embedBuilder.setThumbnail(user.getAvatarUrl());

            embedBuilder.setTimestamp(event.getGuild().getTimeCreated());
            embedBuilder.setFooter("welcome by danilppzz", event.getGuild().getIconUrl());

            textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
        }

        Main.logger.info("Joined - " + event.getMember().getEffectiveName() + " - newValue (" + event.getGuild().getMemberCount() + ")");
    }
}
