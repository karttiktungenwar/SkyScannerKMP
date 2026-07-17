package com.app.skyscanner.core.common.domain.enitity

import kotlinx.datetime.LocalDate

data class Destination(
    val id: String,
    val city: String,
    val country: String,
    val imageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val continent: String,
    val description: String
)

data class ItineraryActivity(
    val period: String,
    val description: String
)

data class ItineraryDay(
    val dayNumber: Int,
    val title: String,
    val imageUrl: String,
    val activities: List<ItineraryActivity>
)

data class Tour(
    val id: String,
    val title: String,
    val days: Int,
    val priceFrom: Int,
    val rating: Double,
    val reviewCount: Int,
    val imageUrl: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val itinerary: List<ItineraryDay>
)