package ru.neuromantics.visittobolsk.di

import org.koin.dsl.module
import ru.neuromantics.visittobolsk.data.network.SharedApi
import ru.neuromantics.visittobolsk.data.network.SharedApiImpl
import ru.neuromantics.visittobolsk.data.repository.CafesRepository
import ru.neuromantics.visittobolsk.data.repository.CafesRepositoryImpl
import ru.neuromantics.visittobolsk.data.repository.HotelsRepository
import ru.neuromantics.visittobolsk.data.repository.HotelsRepositoryImpl
import ru.neuromantics.visittobolsk.data.repository.MuseumsRepository
import ru.neuromantics.visittobolsk.data.repository.MuseumsRepositoryImpl
import ru.neuromantics.visittobolsk.data.repository.ParksRepository
import ru.neuromantics.visittobolsk.data.repository.ParksRepositoryImpl

val dataModule = module {
    single<SharedApi> { SharedApiImpl() }
    single<CafesRepository> { CafesRepositoryImpl(api = get()) }
    single<HotelsRepository> { HotelsRepositoryImpl(api = get()) }
    single<MuseumsRepository> { MuseumsRepositoryImpl(api = get()) }
    single<ParksRepository> { ParksRepositoryImpl(api = get()) }
}