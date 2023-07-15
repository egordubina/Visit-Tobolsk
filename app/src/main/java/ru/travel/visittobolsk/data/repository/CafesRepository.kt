package ru.travel.visittobolsk.data.repository

import kotlinx.coroutines.flow.Flow
import ru.travel.visittobolsk.data.models.CafeNetwork
import ru.travel.visittobolsk.data.models.HotelNetwork
import ru.travel.visittobolsk.data.network.SharedApi
import ru.travel.visittobolsk.domain.models.CafeDomain

interface CafesRepository {
    val cafes: Flow<CafeNetwork>
    val hotels: Flow<HotelNetwork>
    suspend fun loadCafeById(id: Int): CafeNetwork
    suspend fun loadAllCafes(): List<CafeNetwork>
}

class CafesRepositoryImpl(
    private val api: SharedApi,
) : CafesRepository {
    override val cafes: Flow<CafeNetwork> = api.cafes
    override val hotels: Flow<HotelNetwork> = api.hotels
    override suspend fun loadCafeById(id: Int): CafeNetwork = api.loadCafeById(id = id)

    override suspend fun loadAllCafes(): List<CafeNetwork> = api.loadAllCafes()
}