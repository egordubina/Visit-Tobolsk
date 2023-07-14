package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.repository.CafesRepository
import ru.travel.visittobolsk.domain.models.CafeDomain

interface LoadDetailCafeUseCase {
    suspend fun loadCafeById(id: Int): CafeDomain
}

class LoadDetailCafeUseCaseImpl(
    private val cafesRepository: CafesRepository
) : LoadDetailCafeUseCase {
    override suspend fun loadCafeById(id: Int): CafeDomain =
        cafesRepository.loadCafeById(id = id).toDomain()
}