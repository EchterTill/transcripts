package de.till.transcripts.extensions

import de.till.transcripts.classes.Transcript
import de.till.transcripts.classes.TranscriptMessage
import de.till.transcripts.pdf.pdfFromHTML
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction
import net.dv8tion.jda.api.utils.FileUpload

fun MessageChannel.getTranscript(limit: Int): Transcript {
    val history = this.getHistoryFromBeginning(limit).complete()

    return Transcript(history.retrievedHistory.map { TranscriptMessage.from(it) }.reversed())
}

fun MessageChannel.sendTranscript(limit: Int): MessageCreateAction {
    val transcript = this.getTranscript(limit)

    val file = FileUpload.fromData(transcript.toHTML().toByteArray(), "transcript.html")

    return this.sendFiles(file)
}

fun MessageChannel.sendTranscriptAsPDF(limit: Int): MessageCreateAction {
    val transcript = this.getTranscript(limit)

    val pdf = pdfFromHTML(transcript.toHTML())

    return this.sendFiles(FileUpload.fromData(pdf, "transcript.pdf"))


}