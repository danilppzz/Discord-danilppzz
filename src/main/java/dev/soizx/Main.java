package dev.soizx;

import dev.soizx.handler.GuildMemberJoinHandler;
import dev.soizx.util.Load;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("Server");

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        JDA builder = JDABuilder.createDefault(Load.Env("token"))
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

        logger.info("Done (" + ((double) (System.currentTimeMillis() - time)/1000) + "s)");
    }
}