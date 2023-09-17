package com.imgarena.techtask.data

import com.imgarena.techtask.data.JacksonConfiguration.Companion.objectMapper
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.exception.MappingException
import org.slf4j.LoggerFactory

interface Converter<T> {
    fun convert(body: String): GolfTournament

    fun toEntity(body: String): T

    fun dataSourceId(): String

    fun jClass(): Class<T>
}

abstract class GolfTournamentConverter<T> : Converter<T> {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun toEntity(body: String): T =
        try {
            objectMapper.readValue(body, this.jClass())
        } catch (ex: Exception) {
            log.warn("Failed to read from json body: $body")
            throw MappingException(
                "Possible invalid json body: $body",
                ex
            )
        }
}

open class Source()