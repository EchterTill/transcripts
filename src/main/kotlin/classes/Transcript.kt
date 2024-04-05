package de.till.transcripts.classes

import dev.fruxz.ascend.tool.time.calendar.Calendar.FormatStyle
import java.util.*

class Transcript {
    val channel: String
    var messages: List<TranscriptMessage>


    constructor(messages: List<TranscriptMessage>) {
        this.channel = "000000000"
        this.messages = messages
    }

    constructor(channel: String, messages: List<TranscriptMessage>) {
        this.channel = channel
        this.messages = messages
    }


    fun toHTML(): String {
        var lastAuthor = ""
        var html = ""
        // add CSS
        html += "<meta charset=\"UTF-8\">\n"
        html += style

        html += "<h1>Transcript</h1>\n"
        html += "<div class=\"message-history\">\n"

        messages.forEach { message ->
            if (lastAuthor != message.userId) {

                val formatDate = message.time.getFormatted(Locale.GERMAN, FormatStyle.MEDIUM, FormatStyle.SHORT)

                html += "<div class=\"author-message\">"
                html += "<img src=\"${message.avatarUrl}\">"
                html += "<div>"
                html += "<p><b>${message.username}</b><span class=\"author-date\">$formatDate</span></p>"
                html += message.toHTML()
                html += "</div>"
                html += "</div>"

                lastAuthor = message.userId
                return@forEach
            }
            html += message.toHTML()
        }

        html += "</div>"

        return html
    }
}

private val style = """
    <style>
    :root {
        font-family: sans-serif;
        color: white;
        background-color: #313338;
        overflow-wrap: break-word;
    }
    a {
        color: #00a8fc;
    }

    p~ul{
        margin-top: -2px;
        margin-bottom: 4px;
    }
    .user-mention {
        border-radius: .25em;
        padding: .1em;
        background-color: #7289da;
    }
    .user-mention::before {
        content: "@";
    }

    .message-history > p {
        vertical-align: middle;
        position: relative;
        text-indent: -3.5rem;
        padding-left: 3.5rem;
    }

    .message-history > ul {
        padding-left: 4.75rem;
    }
    .message-history > h1, h2, h3, h4{
        padding-left: 3.5rem;
    }

    .message-history > p::before {
        content: attr(time);
        display: inline-block;
        line-height: 100%;
        vertical-align:middle;
        font-size: .7em;
        color: hsl( 214 calc( 1 * 8.1%) 61.2% / 1);
        width: 3rem;
        padding: 0 0.5rem 0 0;
        text-align: center;
        position: relative;
        text-indent: 0;
    }
    .message-history > p, ul {
        margin-top: 4px;
        margin-bottom: 6px;
    }
    .author-message {
        display: flex;
        margin-top: 16px;
    }
    .author-message > img {
        margin-right: .5rem;
        height: 3rem;
        width: 3rem;
        border-radius: 100%;
    }

    .author-message > div {
        display: flex;
        gap: 4px;
        flex-direction: column;
        justify-content: center;
    }

    .author-message > div > p {
        margin: 0;
    }
    
    .author-message > div > ul {
        padding-left: 1.25em;
    }

    .author-date {
        font-size: .7em;
        padding-left: .5em;
        color: hsl( 214 calc( 1 * 8.1%) 61.2% / 1);
    }
    </style>
    """.trimIndent()