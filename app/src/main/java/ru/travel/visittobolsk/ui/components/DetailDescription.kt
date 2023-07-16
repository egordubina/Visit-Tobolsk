package ru.travel.visittobolsk.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun Description(text: String, modifier: Modifier = Modifier) {
    var isOpen by rememberSaveable { mutableStateOf(false) }
    Column(modifier = modifier.animateContentSize()) {
        DescriptionHeader(
            isOpen = isOpen,
            setIsOpen = { isOpen = it },
            hasDescription = text.isNotEmpty()
        )
        Text(
            text = text.ifEmpty { "Описание отсутствует" },
            maxLines = if (isOpen) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun DescriptionHeader(
    isOpen: Boolean,
    setIsOpen: (Boolean) -> Unit,
    hasDescription: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Описание",
            style = MaterialTheme.typography.headlineSmall,
        )
        if (hasDescription)
            IconButton(onClick = { setIsOpen(!isOpen) }) {
                Icon(
                    imageVector = if (isOpen) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                    contentDescription = null
                )
            }
    }
}