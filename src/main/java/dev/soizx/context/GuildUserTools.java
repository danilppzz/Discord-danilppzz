package dev.soizx.context;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildUserTools extends ListenerAdapter {
    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        if (event.getName().equals("GET_AVATAR")) {

            event.reply(event.getTarget().getEffectiveAvatarUrl()).setEphemeral(true).queue();
        }
    }
}
