package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.repository.CafesRepository
import ru.travel.visittobolsk.data.repository.HotelsRepository
import ru.travel.visittobolsk.data.repository.MuseumsRepository
import ru.travel.visittobolsk.data.repository.ParksRepository
import ru.travel.visittobolsk.domain.models.CafeDomain
import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.MuseumDomain
import ru.travel.visittobolsk.domain.models.ParkDomain

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