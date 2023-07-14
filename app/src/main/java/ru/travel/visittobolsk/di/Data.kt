package ru.travel.visittobolsk.di

import org.koin.dsl.module
import ru.travel.visittobolsk.data.network.SharedApi
import ru.travel.visittobolsk.data.network.SharedApiImpl
import ru.travel.visittobolsk.data.repository.CafesRepository
import ru.travel.visittobolsk.data.repository.CafesRepositoryImpl
import ru.travel.visittobolsk.data.repository.HotelsRepository
import ru.travel.visittobolsk.data.repository.HotelsRepositoryImpl
import ru.travel.visittobolsk.data.repository.MuseumsRepository
import ru.travel.visittobolsk.data.repository.MuseumsRepositoryImpl
import ru.travel.visittobolsk.data.repository.ParksRepository
import ru.travel.visittobolsk.data.repository.ParksRepositoryImpl

val dataModule = module {
    single<SharedApi> { SharedApiImpl() }
    single<CafesRepository> { CafesRepositoryImpl(api = get()) }
    single<HotelsRepository> { HotelsRepositoryImpl(api = get()) }
    single<MuseumsRepository> { MuseumsRepositoryImpl(api = get()) }
    single<ParksRepository> { ParksRepositoryImpl(api = get()) }
}