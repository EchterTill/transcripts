package de.till.converter

import net.dv8tion.jda.api.entities.User

object MentionConverter {

    fun convertUserMentions(html: String, users: Map<String, String>): String {
        var output = html
        users.forEach { user ->
            output = output.replace("&lt;@${user.key}&gt;", "<span class=\"user-mention\">${user.value}</span>")

        }

        return output
    }

    fun convertUserMentions(html: String, users: List<User>): String {
        return convertUserMentions(html, users.associate { it.id to it.name })
    }
}