package ru.travel.visittobolsk.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.travel.visittobolsk.ui.components.Description
import ru.travel.visittobolsk.ui.components.DetailAddresses
import ru.travel.visittobolsk.ui.components.DetailContacts
import ru.travel.visittobolsk.ui.components.ImagesSlider
import ru.travel.visittobolsk.ui.components.Schedule
import ru.travel.visittobolsk.ui.uistates.HotelDetailScreenUiState
import ru.travel.visittobolsk.ui.viewmodels.HotelDetailScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(
    vm: HotelDetailScreenViewModel = koinViewModel(),
    onShareButtonClick: (String) -> Unit,
    onBackButtonClick: () -> Unit,
) {
    val uiState = vm.uiState.collectAsState()
    var toolbarTitle by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = toolbarTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
//                actions = {
//                    AnimatedVisibility(toolbarTitle.isNotEmpty()) {
//                        IconButton(onClick = { onShareButtonClick(toolbarTitle) }) {
//                            Icon(
//                                imageVector = Icons.Rounded.Share,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
            )
        }
    ) {
        AnimatedContent(uiState.value, label = "") { state ->
            when (state) {
                is HotelDetailScreenUiState.Content -> {
                    toolbarTitle = state.title
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(it)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ImagesSlider(images = state.images)
                        Description(
                            text = state.description,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Schedule(
                            isOpen = true,
                            time = "",
                            schedule = listOf(),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        DetailAddresses(
                            addresses = listOf(state.address),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        DetailContacts(
                            sites = emptyList(),
                            phones = state.phones,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }

                HotelDetailScreenUiState.Error -> {}
                HotelDetailScreenUiState.Loading -> {}
            }
        }
    }
}