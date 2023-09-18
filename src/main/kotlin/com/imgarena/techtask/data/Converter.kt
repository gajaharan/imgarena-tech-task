package com.imgarena.techtask.data

import com.imgarena.techtask.data.JacksonConfiguration.Companion.objectMapper
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.exception.MappingException
import org.slf4j.LoggerFactory

interface Converter<T> {
    fun convert(body: String): GolfTournament

    fun toEntity(body: String): T =
        try {
            // intellij rejects mapper.readValue() or any other mapper function, but compiles
            objectMapper.readValue(body, this.jClass())
        } catch (ex: Exception) {
            LoggerFactory.getLogger(this::class.java).warn("Failed to read from json body: $body")
            throw MappingException(
                "Possible invalid json body: $body",
                ex
            )
        }

    fun dataSourceId(): String

    fun jClass(): Class<T>
}

open class Source()