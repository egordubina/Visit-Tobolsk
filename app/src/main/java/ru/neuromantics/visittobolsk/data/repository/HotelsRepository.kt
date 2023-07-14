package ru.neuromantics.visittobolsk.data.repository

import ru.neuromantics.visittobolsk.data.models.HotelNetwork
import ru.neuromantics.visittobolsk.data.network.SharedApi

interface HotelsRepository {
    suspend fun loadHotelById(id: Int): HotelNetwork
    suspend fun loadAllHotels(): List<HotelNetwork>
}

class HotelsRepositoryImpl(
    private val api: SharedApi,
) : HotelsRepository {
    override suspend fun loadHotelById(id: Int): HotelNetwork = api.loadHotelById(id = id)

    override suspend fun loadAllHotels(): List<HotelNetwork> = api.loadAllHotels()
}