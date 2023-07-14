package ru.neuromantics.visittobolsk.data.repository

import ru.neuromantics.visittobolsk.data.models.CafeNetwork
import ru.neuromantics.visittobolsk.data.network.SharedApi

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