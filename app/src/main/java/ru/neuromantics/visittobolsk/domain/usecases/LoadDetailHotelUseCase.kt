package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.toDomain
import ru.neuromantics.visittobolsk.domain.models.HotelDomain
import ru.neuromantics.visittobolsk.data.repository.HotelsRepository

interface LoadDetailHotelUseCase {
    suspend fun loadHotelById(id: Int): HotelDomain
}

class LoadDetailHotelUseCaseImpl(
    private val hotelRepository: HotelsRepository
) : LoadDetailHotelUseCase {
    override suspend fun loadHotelById(id: Int): HotelDomain =
        hotelRepository.loadHotelById(id = id).toDomain()
}