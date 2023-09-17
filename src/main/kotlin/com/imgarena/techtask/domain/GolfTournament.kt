package com.imgarena.techtask.domain

import lombok.NonNull
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "golf_tournament")
data class GolfTournament(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull @Id val id: Long = 0,
    @NonNull val externalId: String = "",
    @NonNull val externalSource: String = "",
    @NonNull val courseName: String = "",
    @NonNull val eventName: String = "",
    @NonNull val countryName: String = "",
    @NonNull val startDate: LocalDate = LocalDate.now(),
    @NonNull val endDate: LocalDate = LocalDate.now(),
    @NonNull val rounds: Int = 0
)