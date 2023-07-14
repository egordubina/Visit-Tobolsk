package ru.travel.visittobolsk.di

import org.koin.dsl.module
import ru.travel.visittobolsk.domain.usecases.LoadArUseCase
import ru.travel.visittobolsk.domain.usecases.LoadDetailCafeUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.LoadDetailHotelUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.LoadDetailMuseumUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.LoadDetailParkUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.LoadInterestsScreenUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.SearchUseCaseImpl

val domainModule = module {
    single<LoadInterestsScreenUseCaseImpl> {
        LoadInterestsScreenUseCaseImpl(
            cafesRepository = get(),
            parksRepository = get(),
            museumsRepository = get(),
            hotelsRepository = get()
        )
    }
    single<LoadDetailCafeUseCaseImpl> { LoadDetailCafeUseCaseImpl(cafesRepository = get()) }
    single<LoadDetailMuseumUseCaseImpl> { LoadDetailMuseumUseCaseImpl(museumsRepository = get()) }
    single<LoadDetailParkUseCaseImpl> { LoadDetailParkUseCaseImpl(parkRepository = get()) }
    single<LoadDetailHotelUseCaseImpl> { LoadDetailHotelUseCaseImpl(hotelRepository = get()) }
    single<LoadArUseCase> { LoadArUseCase(api = get()) }
    single<SearchUseCaseImpl> { SearchUseCaseImpl() }
}