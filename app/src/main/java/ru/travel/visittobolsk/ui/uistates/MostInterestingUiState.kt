package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.ParkDomain
import ru.travel.visittobolsk.ui.models.CafeUi
import ru.travel.visittobolsk.ui.models.MuseumUi
import ru.travel.visittobolsk.ui.models.SearchUi

sealed class MostInterestingUiState {
    object Error : MostInterestingUiState()
    object Loading : MostInterestingUiState()
    data class Content(
        val cafesList: List<CafeUi>,
        val museumsList: List<MuseumUi>,
        val parksList: List<ParkDomain>,
        val hotelsList: List<HotelDomain>,
//        val searchResults: List<SearchUi>,
    ) : MostInterestingUiState()
}