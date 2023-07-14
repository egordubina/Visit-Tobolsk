package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.toDomain
import ru.neuromantics.visittobolsk.data.repository.CafesRepository
import ru.neuromantics.visittobolsk.domain.models.CafeDomain

interface LoadDetailCafeUseCase {
    suspend fun loadCafeById(id: Int): CafeDomain
}

class LoadDetailCafeUseCaseImpl(
    private val cafesRepository: CafesRepository
) : LoadDetailCafeUseCase {
    override suspend fun loadCafeById(id: Int): CafeDomain =
        cafesRepository.loadCafeById(id = id).toDomain()
}