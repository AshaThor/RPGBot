package com.ashathor.discord.rpgbot.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class GuildMember {
    private Long getNumber(@Nonnull MessageReceivedEvent event) {
        return event.getMember().getIdLong();
    }

}
