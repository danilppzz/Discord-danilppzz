package dev.soizx.commands;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildAdminTools extends ListenerAdapter {
    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        if (event.getName().equals("getAvatar")) {
            event.reply(event.getTarget().getEffectiveAvatarUrl()).setEphemeral(true).queue();
        }
    }
}
