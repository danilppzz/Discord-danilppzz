package dev.soizx;

import dev.soizx.commands.GuildAdminCommands;
import dev.soizx.commands.GuildMemberCommands;
import dev.soizx.context.GuildMessageContext;
import dev.soizx.context.GuildUserContext;
import dev.soizx.handler.GuildMemberJoinHandler;
import dev.soizx.handler.GuildMemberNameCheckerHandler;
import dev.soizx.util._Load;
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

        JDA builder = JDABuilder.createDefault(_Load.Env("token"))
                .setBulkDeleteSplittingEnabled(false)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.customStatus("⚡ Build in JDA"))
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

        // Commands Builders
        builder.updateCommands().addCommands(
                Commands.context(Command.Type.USER, "GET_AVATAR"),
                Commands.context(Command.Type.MESSAGE, "LOGGER")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                Commands.slash("ping", "Test the response time.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                Commands.slash("rand", "Write a random number between 2 numbers.")
                        .addOption(OptionType.NUMBER, "min", "Min value for rand generator.", true)
                        .addOption(OptionType.NUMBER, "max", "Max value for rand generator.", true)
        ).queue();

        logger.info("Done (" + ((double) (System.currentTimeMillis() - time)/1000) + "s)");
    }
}