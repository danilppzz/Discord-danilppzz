package dev.soizx.context;

import dev.soizx.Main;
import dev.soizx.util.Logger;
import dev.soizx.util.Validations;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageContext extends ListenerAdapter {

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("LOGGER")) {
            User user = event.getInteraction().getUser();
            Logger.start(event, "1158724802459222077", user, 0);
            event.reply("LOGGED").setEphemeral(true).queue();
            Main.logger.info("New Log - "+event.getTarget().getId()+" at "+ Validations.dateNow(Validations.allFormat));
        }
    }
}
