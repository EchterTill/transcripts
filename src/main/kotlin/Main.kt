package de.till
fun main() {
    println("Hello World!")
    val message = "Some *Markdown* <@123456>"

    val htmlMessage = MessageConverter.convert(message)
    val htmlMessageMentions = MessageConverter.replaceEscapedMentions(htmlMessage)
    println(htmlMessageMentions)
}