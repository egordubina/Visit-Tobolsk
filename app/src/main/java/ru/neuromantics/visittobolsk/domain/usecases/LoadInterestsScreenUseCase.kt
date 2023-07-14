package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.toDomain
import ru.neuromantics.visittobolsk.data.repository.CafesRepository
import ru.neuromantics.visittobolsk.data.repository.HotelsRepository
import ru.neuromantics.visittobolsk.data.repository.MuseumsRepository
import ru.neuromantics.visittobolsk.data.repository.ParksRepository
import ru.neuromantics.visittobolsk.domain.models.CafeDomain
import ru.neuromantics.visittobolsk.domain.models.HotelDomain
import ru.neuromantics.visittobolsk.domain.models.MuseumDomain
import ru.neuromantics.visittobolsk.domain.models.ParkDomain

interface LoadInterestsScreenUseCase {
    suspend fun loadAllCafes(): List<CafeDomain>
    suspend fun loadAllParks(): List<ParkDomain>
    suspend fun loadAllMuseums(): List<MuseumDomain>
    suspend fun loadAllHotels(): List<HotelDomain>
}

class LoadInterestsScreenUseCaseImpl(
    private val cafesRepository: CafesRepository,
    private val parksRepository: ParksRepository,
    private val museumsRepository: MuseumsRepository,
    private val hotelsRepository: HotelsRepository,
) : LoadInterestsScreenUseCase {

    override suspend fun loadAllCafes(): List<CafeDomain> = cafesRepository.loadAllCafes().toDomain()

    override suspend fun loadAllParks(): List<ParkDomain> = parksRepository.loadAllParks().toDomain()

    override suspend fun loadAllMuseums(): List<MuseumDomain> = museumsRepository.loadAllMuseums().toDomain()

    override suspend fun loadAllHotels(): List<HotelDomain> = hotelsRepository.loadAllHotels().toDomain()
}