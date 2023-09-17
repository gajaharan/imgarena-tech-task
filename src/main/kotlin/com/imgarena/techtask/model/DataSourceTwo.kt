package com.imgarena.techtask.model

import java.time.Instant

data class DataSourceTwo(
    val tournamentUUID: String,
    val golfCourse: String,
    val competitionName: String,
    val hostCountry: String,
    val epochStart: Instant,
    val epochFinish: Instant,
    val rounds: Int,
    val playerCount: Int
)