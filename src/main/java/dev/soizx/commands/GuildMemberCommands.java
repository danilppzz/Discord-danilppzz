package dev.soizx.commands;

import dev.soizx.util.MathFunctions;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class GuildMemberCommands extends ListenerAdapter {
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
        }
    }

}
