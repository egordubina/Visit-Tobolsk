package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.toDomain
import ru.neuromantics.visittobolsk.data.repository.ParksRepository
import ru.neuromantics.visittobolsk.domain.models.ParkDomain

interface LoadDetailParkUseCase {
    suspend fun loadParkById(id: Int): ParkDomain
}

class LoadDetailParkUseCaseImpl(
    private val parkRepository: ParksRepository
) : LoadDetailParkUseCase {
    override suspend fun loadParkById(id: Int): ParkDomain =
        parkRepository.loadParkById(id = id).toDomain()
}