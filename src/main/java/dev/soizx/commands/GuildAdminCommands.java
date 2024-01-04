package dev.soizx.commands;

import kotlin.collections.builders.ListBuilder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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
        }
    }
}
