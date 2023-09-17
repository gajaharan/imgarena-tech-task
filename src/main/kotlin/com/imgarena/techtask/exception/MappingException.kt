package com.imgarena.techtask.exception

data class MappingException(
    override val message: String = "Invalid JSON",
    val exception: Exception
) : RuntimeException(message, exception)