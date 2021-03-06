package me.sagan.jaseppi.functionality;

import me.sagan.jaseppi.Jaseppi;
import me.sagan.jaseppi.Responses;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author cam (sagan/y0op)
 */
public class Respond extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        Message msg = event.getMessage();

        if (msg.getAuthor().isBot()) return;

        if (!msg.getMentionedUsers().isEmpty()) {
            if (event.getMessage().isMentioned(Jaseppi.jda.getSelfUser())) {
                Jaseppi.send(event.getChannel(), Responses.MENTION_BOT.getRandom());
                return;
            }
        }

        // wont even run if no attachments
        if (msg.getAttachments().stream().anyMatch(attachment -> attachment.isImage() || attachment.isVideo())) {
            if (msg.getTextChannel().getId().equalsIgnoreCase("537425389103087636") &&
                    ThreadLocalRandom.current().nextDouble() > 0.7) {
                Jaseppi.send(event.getChannel(), Responses.IMAGE_VIDEO_RESPONSE.getRandom());
                return;
            }
        }

        if (ThreadLocalRandom.current().nextDouble() > 0.975 && !msg.getContentDisplay().startsWith(Jaseppi.prefix)) {

            String iterable = msg.getContentRaw();
            for (Emote emote : msg.getEmotes()) {
                iterable = iterable.replaceAll(emote.getAsMention(), "");
            }

            StringBuilder newString = new StringBuilder();
            boolean upperCase = false;
            for (char c : iterable.toLowerCase().toCharArray()) {
                if (Character.isSpaceChar(c)) {
                    newString.append(c);
                    continue;
                }

                if (upperCase) {
                    newString.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    newString.append(c);
                    upperCase = true;
                }
            }

            Jaseppi.send(event.getChannel(), newString.toString());
        }
    }
}