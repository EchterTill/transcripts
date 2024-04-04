package de.till

import org.intellij.markdown.IElementType
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object MessageConverter {
    fun convert(message: String): String {
        val flavour = CommonMarkFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour).parse(IElementType("PARAGRAPH"), message)
        return HtmlGenerator(message, parsedTree, flavour).generateHtml()
    }

    fun replaceEscapedMentions(message: String): String {
        return message.replace(Regex("&lt;@!*&*[0-9]+&gt;")) {
            val userId = it.value.drop(5).dropLast(4)
            return@replace "<span class=\"user-mention\" userid=\"$userId\">$userId</span>"
        }
    }


    @Test
    fun testConvert() {
        val expected = "<p><em>Test</em></p>"
        Assertions.assertEquals(expected, convert("*Test*"))
    }

    @Test
    fun testReplaceEscapedMentions() {
        val expected = "<span class=\"user-mention\" userid=\"123456\">Mention</span>"
        Assertions.assertEquals(expected, replaceEscapedMentions("&lt;@123456&gt;"))
    }

}