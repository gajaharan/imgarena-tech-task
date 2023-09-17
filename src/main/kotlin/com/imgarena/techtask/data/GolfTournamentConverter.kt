package com.imgarena.techtask.data

import com.imgarena.techtask.domain.GolfTournament


interface Converter<T> {
    fun convert(body: String): GolfTournament

    fun toEntity(body: String): T

    fun getDataSourceId(): String

    fun getJClass(): Class<T>
}

abstract class GolfTournamentConverter<T> : Converter<T> {

    override fun toEntity(body: String): T =
        JacksonConfiguration.objectMapper.readValue(body, this.getJClass())
}