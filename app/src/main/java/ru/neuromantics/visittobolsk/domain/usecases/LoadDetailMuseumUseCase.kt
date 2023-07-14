package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.toDomain
import ru.neuromantics.visittobolsk.data.repository.MuseumsRepository
import ru.neuromantics.visittobolsk.domain.models.MuseumDomain

interface LoadDetailMuseumUseCase {
    suspend fun loadMuseumById(id: Int): MuseumDomain
}

class LoadDetailMuseumUseCaseImpl(
    private val museumsRepository: MuseumsRepository
) : LoadDetailMuseumUseCase {
    override suspend fun loadMuseumById(id: Int): MuseumDomain =
        museumsRepository.loadMuseumById(id = id).toDomain()

}