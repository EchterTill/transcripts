package de.till.classes

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

        messages.forEach { message ->
            html += message.toHTML()
        }

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
            
    </style>
    """.trimIndent()