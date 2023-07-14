package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.data.repository.HotelsRepository

interface LoadDetailHotelUseCase {
    suspend fun loadHotelById(id: Int): HotelDomain
}

class LoadDetailHotelUseCaseImpl(
    private val hotelRepository: HotelsRepository
) : LoadDetailHotelUseCase {
    override suspend fun loadHotelById(id: Int): HotelDomain =
        hotelRepository.loadHotelById(id = id).toDomain()
}