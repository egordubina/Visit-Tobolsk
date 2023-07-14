package ru.neuromantics.visittobolsk.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.neuromantics.visittobolsk.domain.DateTimeUtil
import ru.neuromantics.visittobolsk.domain.DateTimeUtil.Companion.getDayName
import ru.neuromantics.visittobolsk.ui.theme.VisitTobolskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule(isOpen: Boolean, time: String, schedule: List<String>, modifier: Modifier = Modifier) {
    var fullScheduleIsOpen by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Surface(
            color = if (isOpen) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer,
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = when {
                    isOpen && time.isNotEmpty() -> "Открыто до $time"
                    !isOpen && time.isNotEmpty() -> "Закрыто до $time"
                    isOpen && time.isEmpty() -> "Открыто"
                    !isOpen && time.isEmpty() -> "Закрыто"
                    else -> "Закрыто"
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (isOpen) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(all = 8.dp)
            )
        }
        if (schedule.any { it.isNotEmpty() })
            TextButton(onClick = { fullScheduleIsOpen = true }) {
                Text(text = "Расписание")
                Icon(
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
    }

    if (fullScheduleIsOpen) {
        val currentDay = DateTimeUtil.currentDayOfWeek
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                    fullScheduleIsOpen = false
                }
            },
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Расписание",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                schedule.forEachIndexed { index, item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = getDayName(index),
                            fontSize = if (currentDay == index + 1) 18.sp else 16.sp,
                            fontWeight = if (currentDay == index + 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (currentDay == index + 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = item.ifEmpty { "Нет информации" },
                            fontSize = if (currentDay == index + 1) 18.sp else 16.sp,
                            fontWeight = if (currentDay == index + 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (currentDay == index + 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
@Preview
private fun SchedulePreview() {
    VisitTobolskTheme {
        Schedule(isOpen = false, time = "", schedule = listOf("", "", "1"))
    }
}