package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.ParkDomain
import ru.travel.visittobolsk.ui.models.CafeUi
import ru.travel.visittobolsk.ui.models.MuseumUi
import ru.travel.visittobolsk.ui.models.SearchUi

//sealed class MostInterestingUiState {
//    object Error : MostInterestingUiState()
//    object Loading : MostInterestingUiState()
//    data class Content(
//        val isLoading: Boolean,
//        val isError: Boolean,
//        val cafesList: List<CafeUi>,
//        val museumsList: List<MuseumUi>,
//        val parksList: List<ParkDomain>,
//        val hotelsList: List<HotelDomain>,
////        val searchResults: List<SearchUi>,
//    ) : MostInterestingUiState()
//}

data class MostInterestingUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val cafesList: List<CafeUi> = emptyList(),
    val museumsList: List<MuseumUi> = emptyList(),
    val parksList: List<ParkDomain> = emptyList(),
    val hotelsList: List<HotelDomain> = emptyList(),
)