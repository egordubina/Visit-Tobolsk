package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.repository.MuseumsRepository
import ru.travel.visittobolsk.domain.models.MuseumDomain

interface LoadDetailMuseumUseCase {
    suspend fun loadMuseumById(id: Int): MuseumDomain
}

class LoadDetailMuseumUseCaseImpl(
    private val museumsRepository: MuseumsRepository
) : LoadDetailMuseumUseCase {
    override suspend fun loadMuseumById(id: Int): MuseumDomain =
        museumsRepository.loadMuseumById(id = id).toDomain()

}