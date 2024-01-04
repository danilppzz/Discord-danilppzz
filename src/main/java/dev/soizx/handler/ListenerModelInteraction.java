package dev.soizx.handler;

import dev.soizx.util.Form;
import dev.soizx.util.Validations;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class ListenerModelInteraction extends ListenerAdapter {
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("request_to_dev")) {
            String full_name = Objects.requireNonNull(event.getValue("full_name")).getAsString();
            String github_url = Objects.requireNonNull(event.getValue("github_url")).getAsString();
            String certificate_url = Objects.requireNonNull(event.getValue("certificate_url")).getAsString();

            if (Validations.validateGitHubURL(github_url)) {
                if (Validations.validateURL(certificate_url)) {
                    sendRequest(event);
                    event.reply("Your request has been sent!").setEphemeral(true).queue();
                    Validations.addToCooldownList(event.getInteraction().getUser().getId());
                } else {
                    event.reply("Error validating at ( certificate_url )").setEphemeral(true).queue();
                }
            } else {
                event.reply("Error validating at ( github_url )").setEphemeral(true).queue();
            }
        }
    }

    private void sendRequest(ModalInteractionEvent event) {
        TextChannel textChannel  = event.getJDA().getTextChannelById("1158724878430650378");

        String full_name = Objects.requireNonNull(event.getValue("full_name")).getAsString();
        String github_url = Objects.requireNonNull(event.getValue("github_url")).getAsString();
        String certificate_url = Objects.requireNonNull(event.getValue("certificate_url")).getAsString();

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Color.decode("#4F90DF"));
        embedBuilder.setThumbnail(event.getUser().getAvatarUrl());
        embedBuilder.setTitle(event.getUser().getId());

        embedBuilder.addField("Created at","` "+event.getTimeCreated()+" `",true);
        embedBuilder.addField("Author ", event.getUser().getAsMention(),true);
        embedBuilder.addField("","", false);
        embedBuilder.addField("Full name", full_name,true);
        embedBuilder.addField("Github url","["+Validations.getGitHubUsername(github_url)+"]("+github_url+")",true);
        embedBuilder.addField("Certificate url","[Certificate]("+certificate_url+")",true);


        assert textChannel != null;
        textChannel.sendMessageEmbeds(embedBuilder.build())
                .addActionRow(
                        Button.primary("request_to_dev_accept", "Accept"),
                        Button.danger("request_to_dev_reject", "Reject")
                ).queue();
    }
}
