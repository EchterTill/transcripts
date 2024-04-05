package classes

import de.till.transcripts.classes.TranscriptMessage
import dev.fruxz.ascend.tool.time.calendar.Calendar
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TranscriptMessageTest {
    @Test
    fun testToHTML() {
        val expected = "<p><em><strong>Hello</strong></em> <span class=\"user-mention\">world</span></p>"
        val message = TranscriptMessage(
            "***Hello*** <@1234>", "echtertill", "1234", Calendar.now(), mapOf(Pair<String, String>("1234", "world"))
        )
        Assertions.assertEquals(expected, message.toHTML())
    }
}