package de.till.transcripts.pdf

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * Converts the HTML String to a PDF document.
 * @param html HTML document as string
 * @return PDF document as InputStream
 *
 * @author EchterTill
 * @author CoasterFreakDE
 */
internal fun pdfFromHTML(html: String): InputStream {

    val outputStream = ByteArrayOutputStream()
    try {
        val builder = PdfRendererBuilder()
        builder.useFastMode()
        builder.withHtmlContent(html, null)
        builder.toStream(outputStream)
        builder.run()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return outputStream.toByteArray().inputStream()
}
