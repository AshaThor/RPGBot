package com.ashathor.discord.rpgbot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class InitiativeQueue {
    public void create(@Nonnull MessageReceivedEvent event, String[] userCommand){
        String[] userArray = new String[Integer.parseInt(userCommand[1])] ;
        EmbedBuilder teb = new EmbedBuilder();
        teb.setTitle("Initiative Queue");
        teb.setDescription("Click the emoji to roll initiative");
        teb.addField("Players", String.valueOf(userArray.length),true);
        event.getChannel().sendMessage(teb.build()).queue(message -> message.addReaction("U+1F3B2").queue());
    }
    public void upddate(){
        /* JDA peeps told me this
        AshaThor - Is it possible to update an embeded message once sent?
        DManstrator - retrieveMessageById -> ErrorResponse -> Unknown Message, yes. Get access to the message
        (success consumer after sending / retrieveMessageById), edit it (with a new Embed (new EmbedBuilder(ember)), maybe override it (check methods) an send it again.
        *
        * */

    }
}
