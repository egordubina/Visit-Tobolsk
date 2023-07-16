package ru.travel.visittobolsk.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import ru.travel.visittobolsk.data.TempData
import ru.travel.visittobolsk.data.models.ArModel
import ru.travel.visittobolsk.data.models.CafeNetwork
import ru.travel.visittobolsk.data.models.HotelNetwork
import ru.travel.visittobolsk.data.models.MuseumNetwork
import ru.travel.visittobolsk.data.models.ParkNetwork

private const val WEATHER_KEY = "53af612e6252b27564da95ca8b0a540c"
private const val WEATHER_BASE_URL =
    "https://api.openweathermap.org/data/2.5/weather?lat=58.196670&lon=68.233810&appid=$WEATHER_KEY"

interface SharedApi {
    suspend fun loadAllCafes(): List<CafeNetwork>
    suspend fun loadCafeById(id: Int): CafeNetwork

    suspend fun loadAllMuseums(): List<MuseumNetwork>
    suspend fun loadMuseumById(id: Int): MuseumNetwork

    suspend fun loadAllParks(): List<ParkNetwork>
    suspend fun loadParkById(id: Int): ParkNetwork

    suspend fun loadAllHotels(): List<HotelNetwork>
    suspend fun loadHotelById(id: Int): HotelNetwork

    suspend fun loadArModels(): List<ArModel>
    suspend fun loadCurrentWeather(): String
}

class SharedApiImpl : SharedApi {
    private val client: HttpClient = HttpClient(CIO)

    override suspend fun loadAllCafes(): List<CafeNetwork> {
        delay(1000)
        return TempData.cafes
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
            delay(1200)
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
            delay(800)
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
            delay(850)
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

    override suspend fun loadCurrentWeather(): String {
        return try {
            val request = client.get(WEATHER_BASE_URL).bodyAsText()
            Log.d("Weather API", request)
            request
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }
}