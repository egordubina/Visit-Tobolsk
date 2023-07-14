package ru.travel.visittobolsk.data.repository

import ru.travel.visittobolsk.data.models.CafeNetwork
import ru.travel.visittobolsk.data.network.SharedApi

interface CafesRepository {
    suspend fun loadCafeById(id: Int): CafeNetwork
    suspend fun loadAllCafes(): List<CafeNetwork>
}

class CafesRepositoryImpl(
    private val api: SharedApi,
) : CafesRepository {
    override suspend fun loadCafeById(id: Int): CafeNetwork = api.loadCafeById(id = id)

    override suspend fun loadAllCafes(): List<CafeNetwork> = api.loadAllCafes()
}