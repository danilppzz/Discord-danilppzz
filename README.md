# Discord Bot Soizx

First you need to clone this repo from the command or download the zip package.
```
git clone https://github.com/soizx/Discord-Soizx
```
Then open the project or directly run the bot but first you need to type your discord bot token. for that you have to create a file in resources called ` .env ` and write.
```env
TOKEN=YOUR_BOT_TOKEN
```

# Intents
Be carefull with the intents, by default in my project are.
```java
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
```
