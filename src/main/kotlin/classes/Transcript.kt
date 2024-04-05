package de.till.transcripts.classes

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
        var html = ""
        // add CSS
        html += style

        html += "<h1>Transcript</h1>\n"
        html += "<div class=\"message-history\">\n"

        messages.forEach { message ->
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

    }
            
    </style>
    """.trimIndent()