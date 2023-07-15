package ru.travel.visittobolsk.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.travel.visittobolsk.data.TempData
import ru.travel.visittobolsk.data.models.ArModel
import ru.travel.visittobolsk.data.models.CafeNetwork
import ru.travel.visittobolsk.data.models.HotelNetwork
import ru.travel.visittobolsk.data.models.MuseumNetwork
import ru.travel.visittobolsk.data.models.ParkNetwork

interface SharedApi {
    val cafes: Flow<List<CafeNetwork>>
    val parks: Flow<List<ParkNetwork>>
    val hotels: Flow<List<HotelNetwork>>
    fun loadAllCafes(): Flow<CafeNetwork>
    suspend fun loadCafeById(id: Int): CafeNetwork

    suspend fun loadAllMuseums(): List<MuseumNetwork>
    suspend fun loadMuseumById(id: Int): MuseumNetwork

    suspend fun loadAllParks(): List<ParkNetwork>
    suspend fun loadParkById(id: Int): ParkNetwork

    suspend fun loadAllHotels(): List<HotelNetwork>
    suspend fun loadHotelById(id: Int): HotelNetwork

    suspend fun loadArModels(): List<ArModel>
}

class SharedApiImpl : SharedApi {
    override val cafes: Flow<List<CafeNetwork>> = flow {
        emit(TempData.cafes)
    }
    override val parks: Flow<List<ParkNetwork>> = flow {
        emit(TempData.parks)
    }
    override val hotels: Flow<List<HotelNetwork>> = flow {
        emit(TempData.hotels)
    }
    override fun loadAllCafes(): Flow<CafeNetwork> {
        return flow {
            TempData.cafes.forEach { emit(it) }
        }
//        return try {
//            TempData.cafes
//        } catch (e: Exception) {
//            throw RuntimeException(e.toString())
//        }
    }

    override suspend fun loadCafeById(id: Int): CafeNetwork {
        return try {
            TempData.cafes[id]
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllMuseums(): List<MuseumNetwork> {
        return try {
            TempData.museums
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadMuseumById(id: Int): MuseumNetwork {
        return try {
            TempData.museums[id]
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllParks(): List<ParkNetwork> {
        return try {
            TempData.parks
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadParkById(id: Int): ParkNetwork {
        return try {
            TempData.parks[id]
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllHotels(): List<HotelNetwork> {
        return try {
            TempData.hotels
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadHotelById(id: Int): HotelNetwork {
        return try {
            TempData.hotels[id]
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadArModels(): List<ArModel> {
        return try {
            emptyList()
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }
}