package de.till.transcripts.classes

import dev.fruxz.ascend.extension.time.hour
import dev.fruxz.ascend.extension.time.minute

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
        html += style

        html += "<h1>Transcript</h1>\n"
        html += "<div class=\"message-history\">\n"

        messages.forEach { message ->
            if (lastAuthor != message.userId) {
                html += "<div class=\"author-message\">"
                html += "<img src=\"https://picsum.photos/200\">"
                html += "<div>"
                html += "<p><u><b>${message.username}</u></b><span class=\"author-date\">${message.time.hour}:${message.time.minute}</span></p>"
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
        background-color: #424549;
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
    }

    .message-history > p::before {
        content: attr(time);
        display: inline-block;
        line-height: 100%;
        vertical-align:middle;
        font-size: .7em;
        padding-right: .33em;
        color: hsl( 214 calc( 1 * 8.1%) 61.2% / 1);
        width: 3rem;
        padding: 0 0.5rem 0 0;
        text-align: center;
    }
    .message-history > p {
        margin-top: 4px;
        margin-bottom: 8px;
    }
    .author-message {
        display: flex;
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

    .author-date {
        font-size: .7em;
        padding-left: .33em;
        color: hsl( 214 calc( 1 * 8.1%) 61.2% / 1);
    }
    </style>
    """.trimIndent()