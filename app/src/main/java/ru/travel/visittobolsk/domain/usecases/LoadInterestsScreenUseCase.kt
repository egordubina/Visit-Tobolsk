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
//    val allCafes: Flow<List<CafeDomain>>
//    val allHotels: Flow<List<HotelDomain>>
//    val allParks: Flow<List<ParkDomain>>
    suspend fun loadAllCafes(): List<CafeDomain>
    suspend fun loadAllHotels(): List<HotelDomain>
    suspend fun loadAllParks(): List<ParkDomain>
}

class LoadInterestsScreenUseCaseImpl(
    private val cafesRepository: CafesRepository,
    private val parksRepository: ParksRepository,
    private val museumsRepository: MuseumsRepository,
    private val hotelsRepository: HotelsRepository,
) : LoadInterestsScreenUseCase {

    //    override val allCafes: Flow<List<CafeDomain>> = cafesRepository.cafes.map { it.toDomain() }
//    override val allHotels: Flow<List<HotelDomain>> = hotelsRepository.hotels.map { it.toDomain() }
//    override val allParks: Flow<List<ParkDomain>> = parksRepository.parks.map { it.toDomain() }
    override suspend fun loadAllCafes(): List<CafeDomain> = cafesRepository.loadAllCafes().toDomain()
    override suspend fun loadAllHotels(): List<HotelDomain> = hotelsRepository.loadAllHotels().toDomain()
    override suspend fun loadAllParks(): List<ParkDomain> = parksRepository.loadAllParks().toDomain()
}