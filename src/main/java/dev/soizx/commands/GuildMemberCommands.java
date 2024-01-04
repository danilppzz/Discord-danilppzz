package dev.soizx.commands;

import dev.soizx.util.MathFunctions;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GuildMemberCommands extends ListenerAdapter {
    private final Map<String, Instant> lastRequestTimes = new HashMap<>();
    private final Duration requestCooldown = Duration.ofDays(7);

    public boolean canMakeRequest(String userId) {
        Instant lastRequestTime = lastRequestTimes.get(userId);
        Instant currentTime = Instant.now();

        if (lastRequestTime == null || Duration.between(lastRequestTime, currentTime).compareTo(requestCooldown) >= 0) {
            lastRequestTimes.put(userId, currentTime);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("rand")) {
            OptionMapping minOption = event.getOption("min");
            OptionMapping maxOption = event.getOption("max");

            assert maxOption != null;
            assert minOption != null;
            double min = minOption.getAsDouble();
            double max = maxOption.getAsDouble();

            int minInt = (int) min;
            int maxInt = (int) max;
            event.reply(MathFunctions.rand(minInt, maxInt)+"").queue();
        } else if (event.getName().equals("reqdev")) {
            if (!canMakeRequest(Objects.requireNonNull(event.getInteraction().getUser().getId()))) {
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

            TextInput certificate = TextInput.create("certificate_url", "Certificate Url", TextInputStyle.SHORT)
                    .setPlaceholder("Type the url to your certificate.")
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();


            Modal modal = Modal.create("request_to_dev", "Request to developer.")
                    .addComponents(ActionRow.of(name), ActionRow.of(github), ActionRow.of(certificate))
                    .build();

            event.replyModal(modal).queue();
        }
    }

}
