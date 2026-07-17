package com.app.skyscanner.core.common.domain.repository

import com.app.skyscanner.core.common.domain.enitity.Destination
import com.app.skyscanner.core.common.domain.enitity.Tour

interface TravelRepository {
    suspend fun getCategories(): List<String>
    suspend fun getDestinationsByContinent(continent: String?): List<Destination>
    suspend fun getDestinationById(id: String): Destination?
    suspend fun getToursForDestination(destinationId: String): List<Tour>
    suspend fun getTourById(tourId: String): Tour?
}