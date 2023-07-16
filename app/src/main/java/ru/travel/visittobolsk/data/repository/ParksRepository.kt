package ru.travel.visittobolsk.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.travel.visittobolsk.data.models.ParkNetwork
import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.network.SharedApi
import ru.travel.visittobolsk.domain.models.ParkDomain

interface ParksRepository {
    suspend fun loadAllParks(): List<ParkNetwork>
    suspend fun loadParkById(id: Int): ParkNetwork
}

class ParksRepositoryImpl(
    private val api: SharedApi,
) : ParksRepository {
    override suspend fun loadAllParks(): List<ParkNetwork> = api.loadAllParks()

    override suspend fun loadParkById(id: Int): ParkNetwork = api.loadParkById(id = id)
}