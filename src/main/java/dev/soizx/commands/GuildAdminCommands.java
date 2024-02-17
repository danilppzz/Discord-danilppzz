package dev.soizx.commands;

import dev.soizx.util.SystemPerformance;
import dev.soizx.util.Validations;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuildAdminCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            long time = System.currentTimeMillis();
            event.reply("Pong").setEphemeral(true).flatMap(v -> event.getHook().editOriginalFormat("Pong ` %d ms `", System.currentTimeMillis() - time)).queue();
        } else if (event.getName().equals("links")) {
            List<Invite> invites = Objects.requireNonNull(event.getGuild()).retrieveInvites().complete();
            List<String> urls = new ArrayList<>();

            for (Invite invite : invites) {
                urls.add(invite.getUrl());
            }

            event.reply(urls.toString()).setEphemeral(true).queue();
        } else if (event.getName().equals("stats")) {
            String osName = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle(Validations.dateNow(Validations.allFormat));
            embedBuilder.setDescription("Current stats of the bot and the server.");
            embedBuilder.setColor(Color.decode("#4F90DF"));
            embedBuilder.setThumbnail(Objects.requireNonNull(event.getGuild()).getIconUrl());

            embedBuilder.addBlankField(false);

            embedBuilder.addField("Performance", "```RAM: " + SystemPerformance.getUsageMemory() +" / "+ SystemPerformance.getTotalMemory() + " MB \nCPU: "+ SystemPerformance.getUsageCores() +" / "+ SystemPerformance.getTotalCores() + " cores```", false);

            embedBuilder.addField("Operative System", "```" + osName + " " + osVersion + "```", false);

            embedBuilder.addField("Member Count", "```" + event.getGuild().getMemberCount() + " Members" + "```", true);
            embedBuilder.addField("Server Boosts", "```" + event.getGuild().getBoostCount() + " Boosts" + "```", true);
            embedBuilder.addField("Server Owner", "```" + Objects.requireNonNull(event.getGuild().getOwner()).getEffectiveName() + "```", true);

            embedBuilder.setTimestamp(event.getTimeCreated());
            embedBuilder.setFooter("stats by danilppzz", event.getGuild().getIconUrl());

            event.replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();
        } else if (event.getName().equals("clear")) {
            TextChannel textChannel = (TextChannel) event.getChannel();
            textChannel.purgeMessages(textChannel.getIterableHistory().complete());
            event.reply("Successfully cleared").setEphemeral(true).queue();
        }
    }
}
