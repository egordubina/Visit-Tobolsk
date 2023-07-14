package ru.travel.visittobolsk.data.repository

import ru.travel.visittobolsk.data.models.HotelNetwork
import ru.travel.visittobolsk.data.network.SharedApi

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