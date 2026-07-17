package com.app.skyscanner.core.common.data.repository

import com.app.skyscanner.core.common.data.models.ModelData
import com.app.skyscanner.core.common.domain.enitity.Destination
import com.app.skyscanner.core.common.domain.enitity.Tour
import com.app.skyscanner.core.common.domain.repository.TravelRepository

class TravelRepositoryImpl : TravelRepository {
    override suspend fun getCategories(): List<String> = ModelData.categories

    override suspend fun getDestinationsByContinent(continent: String?): List<Destination> {
        if (continent.isNullOrBlank()) return ModelData.destinations
        return ModelData.destinations.filter { it.continent.equals(continent, ignoreCase = true) }
    }

    override suspend fun getDestinationById(id: String): Destination? {
        return ModelData.destinations.find { it.id == id }
    }

    override suspend fun getToursForDestination(destinationId: String): List<Tour> {
        return ModelData.toursForRio
    }

    override suspend fun getTourById(tourId: String): Tour? {
        return ModelData.toursForRio.firstOrNull { it.id == tourId } ?: ModelData.iconicBrazil
    }
}