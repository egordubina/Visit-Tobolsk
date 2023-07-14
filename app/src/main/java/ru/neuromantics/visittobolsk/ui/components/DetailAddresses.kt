package ru.neuromantics.visittobolsk.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.neuromantics.visittobolsk.data.models.Address

@Composable
fun DetailAddresses(addresses: List<Address>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = "Адреса",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            addresses.forEach {
                AddressItem(address = it)
            }
        }
    }
}

@Composable
private fun AddressItem(address: Address) {
    val context = LocalContext.current
    ElevatedButton(onClick = {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:0,0?q=${address.addressName}")
        )
        val chooser = Intent.createChooser(
            intent,
            "${address.addressName} на карте"
        )
        context.startActivity(chooser)
    }) {
        Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = null)
        Text(text = address.addressName, modifier = Modifier.padding(horizontal = 4.dp))
        Icon(imageVector = Icons.Rounded.ArrowForwardIos, contentDescription = null)
    }
}