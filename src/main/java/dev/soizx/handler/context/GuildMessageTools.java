package dev.soizx.handler.context;

import dev.soizx.util._Logger;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageTools extends ListenerAdapter {

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("LOGGER")) {
            User user = event.getInteraction().getUser();
            _Logger.start(event, "1163433569855737898", user, 0);
        }
    }
}
