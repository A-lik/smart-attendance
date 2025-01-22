package edu.iitu.smartattendance.domain.auth.model

@JvmInline
value class Email private constructor(val value: String) {

    companion object {
        private val emailRegex = Regex(pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

        fun from (value: String): Email? = if(emailRegex.containsMatchIn(value))
            Email(value) else null
    }
}