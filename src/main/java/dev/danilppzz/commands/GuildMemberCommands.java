package dev.danilppzz.commands;

import dev.danilppzz.util.Validations;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.Objects;

public class GuildMemberCommands extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("reqdev")) {
            if (!Validations.isInCooldownList(Objects.requireNonNull(event.getInteraction().getUser().getId()))) {
                event.reply("You can only make one request per week.").queue();
                return;
            }
            TextInput name = TextInput.create("full_name", "Full name", TextInputStyle.SHORT)
                    .setPlaceholder("Write your full name.")
                    .setMinLength(10)
                    .setMaxLength(80)
                    .build();

            TextInput github = TextInput.create("github_url", "Github Url", TextInputStyle.SHORT)
                    .setPlaceholder("Type the url to your github profile.")
                    .setMinLength(10)
                    .setMaxLength(80)
                    .build();

            TextInput certificate = TextInput.create("certificate_url", "JS Certificate Url", TextInputStyle.SHORT)
                    .setPlaceholder("Type the url to your certificate.")
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();


            Modal modal = Modal.create("request_to_dev", "Dev Request ( 24 to 48 hours )")
                    .addComponents(ActionRow.of(name), ActionRow.of(github), ActionRow.of(certificate))
                    .build();

            event.replyModal(modal).queue();
        }
    }

}
