package com.imgarena.techtask.exception

data class ConverterNotFoundException(
    override val message: String = "Converter not found"
) : RuntimeException(message)