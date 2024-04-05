package de.till.transcripts.classes

import de.till.transcripts.converter.MentionConverter
import dev.fruxz.ascend.extension.time.hour
import dev.fruxz.ascend.extension.time.minute
import dev.fruxz.ascend.tool.time.calendar.Calendar
import net.dv8tion.jda.api.entities.Message
import org.intellij.markdown.IElementType
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

class TranscriptMessage {
    val content: String
    val username: String
    val userId: String
    val time: Calendar
    val avatarUrl: String?
    val mentions: Map<String, String>

    constructor(
        content: String, username: String, userId: String, time: Calendar, mentions: Map<String, String> = emptyMap()
    ) {
        this.content = content
        this.username = username
        this.userId = userId
        this.time = time
        this.mentions = mentions
        this.avatarUrl = "https://picsum.photos/200"
    }

    private constructor(message: Message) {
        content = message.contentRaw
        username = message.author.name
        userId = message.author.id
        time = Calendar(message.timeCreated.toLocalDateTime())
        mentions = message.mentions.users.associate { it.id to it.name }
        avatarUrl = message.author.avatarUrl


    }

    companion object {
        fun from(message: Message): TranscriptMessage {
            return TranscriptMessage(message)
        }
    }

    fun toHTML(): String {

        // Convert Markdown to <p>
        val flavour = CommonMarkFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour).parse(IElementType("PARAGRAPH"), content)
        var html = HtmlGenerator(content, parsedTree, flavour).generateHtml()

        // Mentions to html tags
        html = MentionConverter.convertUserMentions(html, mentions)

        // Add Timestamp
        html = html.replace("<p>", "<p time=\"${time.hour}:${time.minute}\">")
        return html
    }

}