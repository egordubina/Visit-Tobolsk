package ru.travel.visittobolsk.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.travel.visittobolsk.data.models.toDomain
import ru.travel.visittobolsk.data.repository.CafesRepository
import ru.travel.visittobolsk.data.repository.HotelsRepository
import ru.travel.visittobolsk.data.repository.MuseumsRepository
import ru.travel.visittobolsk.data.repository.ParksRepository
import ru.travel.visittobolsk.domain.models.CafeDomain
import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.ParkDomain

interface LoadInterestsScreenUseCase {
//    suspend fun loadAllCafes(): Flow<CafeDomain>
//    suspend fun loadAllParks(): List<ParkDomain>
//    suspend fun loadAllMuseums(): List<MuseumDomain>
//    suspend fun loadAllHotels(): List<HotelDomain>
}

class LoadInterestsScreenUseCaseImpl(
    private val cafesRepository: CafesRepository,
    private val parksRepository: ParksRepository,
    private val museumsRepository: MuseumsRepository,
    private val hotelsRepository: HotelsRepository,
) : LoadInterestsScreenUseCase {
    val allCafes: Flow<List<CafeDomain>> = cafesRepository.cafes.map { it.toDomain() }
    val allHotels: Flow<List<HotelDomain>> = hotelsRepository.hotels.map { it.toDomain() }
    val allParks: Flow<List<ParkDomain>> = parksRepository.parks.map { it.toDomain() }

//    override suspend fun loadAllCafes(): Flow<CafeDomain> = cafesRepository.loadAllCafes().map { it.toDomain() }
//
//    override suspend fun loadAllParks(): List<ParkDomain> = parksRepository.loadAllParks().toDomain()
//
//    override suspend fun loadAllMuseums(): List<MuseumDomain> = museumsRepository.loadAllMuseums().toDomain()
//
//    override suspend fun loadAllHotels(): List<HotelDomain> = hotelsRepository.loadAllHotels().toDomain()
}