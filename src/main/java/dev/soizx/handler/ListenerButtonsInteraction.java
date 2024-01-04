package dev.soizx.handler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListenerButtonsInteraction extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("request_to_dev_accept")) {
            User user = event.getJDA().getUserById(Objects.requireNonNull(event.getMessage().getEmbeds().get(0).getTitle()));

            assert user != null;
            PrivateChannel privateChannel = user.openPrivateChannel().complete();

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setColor(Color.decode("#2981FF"));
            embedBuilder.setThumbnail(user.getAvatarUrl());
            embedBuilder.setTitle("Congratulations! ");
            embedBuilder.addField("message","You have obtained the rank of developer on the danilppzz discord server.", false);

            assert privateChannel != null;
            privateChannel.sendMessageEmbeds(embedBuilder.build()).addActionRow(Button.link("https://discord.com/channels/1158719173891985500", "Go to the server!")).queue();

            event.reply("request_to_dev_accept").setEphemeral(true).queue();
            event.getMessage().editMessageComponents(event.getMessage().getActionRows().stream().map(ActionRow::asDisabled).toList()).queue();

        } else if (event.getComponentId().equals("request_to_dev_reject")) {
            User user = event.getJDA().getUserById(Objects.requireNonNull(event.getMessage().getEmbeds().get(0).getTitle()));

            assert user != null;
            PrivateChannel privateChannel = user.openPrivateChannel().complete();

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setColor(Color.decode("#FF2929"));
            embedBuilder.setThumbnail(user.getAvatarUrl());
            embedBuilder.setTitle("We're sorry! ");
            embedBuilder.addField("message", "Your developer rank request has been rejected on the danilppzz discord server.", false);

            assert privateChannel != null;
            privateChannel.sendMessageEmbeds(embedBuilder.build()).addActionRow(Button.link("https://discord.com/channels/1158719173891985500", "Go to the server!")).queue();

            event.getInteraction().getMessage().delete().queue();

            event.reply("request_to_dev_reject").setEphemeral(true).queue();
        }
    }
}
