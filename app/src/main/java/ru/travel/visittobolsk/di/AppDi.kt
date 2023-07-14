package ru.travel.visittobolsk.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.travel.visittobolsk.ui.viewmodels.ArViewModel
import ru.travel.visittobolsk.ui.viewmodels.CafeDetailScreenViewModel
import ru.travel.visittobolsk.ui.viewmodels.HotelDetailScreenViewModel
import ru.travel.visittobolsk.ui.viewmodels.MostInterestsViewModel
import ru.travel.visittobolsk.ui.viewmodels.MuseumDetailScreenViewModel
import ru.travel.visittobolsk.ui.viewmodels.ParkDetailScreenViewModel

val appModule = module {
    viewModel<MostInterestsViewModel> { MostInterestsViewModel(loadScreenUseCase = get(), searchUseCase = get()) }
    viewModel<CafeDetailScreenViewModel> { CafeDetailScreenViewModel(savedStateHandle = get(), loadDetailCafeUseCase = get()) }
    viewModel<MuseumDetailScreenViewModel> { MuseumDetailScreenViewModel(savedStateHandle = get(), loadDetailMuseumUseCase = get()) }
    viewModel<ParkDetailScreenViewModel> { ParkDetailScreenViewModel(savedStateHandle = get(), loadDetailParkUseCase = get()) }
    viewModel<HotelDetailScreenViewModel> { HotelDetailScreenViewModel(savedStateHandle = get(), loadDetailHotelUseCase = get()) }
    viewModel<ArViewModel> { ArViewModel(loadArUseCase = get()) }
}