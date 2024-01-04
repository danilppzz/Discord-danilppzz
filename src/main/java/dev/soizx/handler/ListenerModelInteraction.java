package dev.soizx.handler;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ListenerModelInteraction extends ListenerAdapter {
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("request_to_dev")) {
            String full_name = Objects.requireNonNull(event.getValue("full_name")).getAsString();
            String github_url = Objects.requireNonNull(event.getValue("github_url")).getAsString();
            String certificate_url = Objects.requireNonNull(event.getValue("certificate_url")).getAsString();

            event.reply("Your request has been sent!").setEphemeral(true).queue();
        }
    }
}
