package com.imgarena.techtask.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate

data class DataSourceOne(
    val tournamentId: String,
    val tournamentName: String,
    val forecast: String,
    val courseName: String,
    val countryCode: String,
    @JsonFormat(shape = STRING, pattern = "dd/MM/yy")
    val startDate: LocalDate,
    @JsonFormat(shape = STRING, pattern = "dd/MM/yy")
    val endDate: LocalDate,
    val roundCount: Int
)