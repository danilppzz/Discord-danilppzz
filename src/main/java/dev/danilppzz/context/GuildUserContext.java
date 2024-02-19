package dev.danilppzz.context;

import dev.danilppzz.Main;
import dev.danilppzz.util.Validations;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildUserContext extends ListenerAdapter {
    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        if (event.getName().equals("GET_AVATAR")) {
            event.reply(event.getTarget().getEffectiveAvatarUrl()).setEphemeral(true).queue();
            Main.logger.info("New GET_AVATAR - "+event.getTarget().getId()+" at "+ Validations.dateNow(Validations.allFormat));
        }
    }
}
