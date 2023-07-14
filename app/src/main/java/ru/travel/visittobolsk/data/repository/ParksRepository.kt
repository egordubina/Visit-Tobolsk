package ru.travel.visittobolsk.data.repository

import ru.travel.visittobolsk.data.models.ParkNetwork
import ru.travel.visittobolsk.data.network.SharedApi

interface ParksRepository {
    suspend fun loadParkById(id: Int): ParkNetwork
    suspend fun loadAllParks(): List<ParkNetwork>
}

class ParksRepositoryImpl(
    private val api: SharedApi,
) : ParksRepository {
    override suspend fun loadParkById(id: Int): ParkNetwork = api.loadParkById(id = id)

    override suspend fun loadAllParks(): List<ParkNetwork> = api.loadAllParks()
}