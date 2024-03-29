package dev.danilppzz;

import dev.danilppzz.commands.GuildAdminCommands;
import dev.danilppzz.commands.GuildMemberCommands;
import dev.danilppzz.context.GuildMessageContext;
import dev.danilppzz.context.GuildUserContext;
import dev.danilppzz.handler.*;
import dev.danilppzz.util.LoadConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("Server");

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        JDA builder = JDABuilder.createDefault(LoadConfiguration.Env("token"))
                .setBulkDeleteSplittingEnabled(false)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.customStatus("🤍 Build in JDA"))
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_MODERATION,
                        GatewayIntent.AUTO_MODERATION_CONFIGURATION,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.SCHEDULED_EVENTS
                )
                .build();

        // Builds Events
        builder.addEventListener(new GuildMemberJoinHandler());
        builder.addEventListener(new GuildMemberNameCheckerHandler());
        builder.addEventListener(new GuildAdminCommands());
        builder.addEventListener(new GuildMemberCommands());
        builder.addEventListener(new GuildUserContext());
        builder.addEventListener(new GuildMessageContext());
        builder.addEventListener(new ListenerModelInteraction());
        builder.addEventListener(new ListenerButtonsInteraction());
        builder.addEventListener(new ListenerCommandAutoComplete());

        // Commands Builders
        builder.updateCommands().addCommands(
                Commands.context(Command.Type.USER, "GET_AVATAR"),
                Commands.context(Command.Type.MESSAGE, "LOGGER")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                Commands.slash("ping", "Test the response time.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                Commands.slash("links", "Get active links on the server."),
                Commands.slash("reqdev", "Request to the developer rank.")
                        .addOption(OptionType.STRING, "language", "Select your language.", true, true),
                Commands.slash("stats", "Return the stats of the server and the bot.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                Commands.slash("clear", "Clear all message in the text channel.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))

        ).queue();

        logger.info("Done (" + ((double) (System.currentTimeMillis() - time)/1000) + "s)");
    }
}