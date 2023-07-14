package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.repository.ParksRepository
import ru.travel.visittobolsk.domain.models.ParkDomain

interface LoadDetailParkUseCase {
    suspend fun loadParkById(id: Int): ParkDomain
}

class LoadDetailParkUseCaseImpl(
    private val parkRepository: ParksRepository
) : LoadDetailParkUseCase {
    override suspend fun loadParkById(id: Int): ParkDomain =
        parkRepository.loadParkById(id = id).toDomain()
}