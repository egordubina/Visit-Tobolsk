package ru.neuromantics.visittobolsk.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CurrencyRuble
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.neuromantics.visittobolsk.R
import ru.neuromantics.visittobolsk.data.TempData
import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.ui.theme.VisitTobolskTheme

@Composable
fun ItemCard(
    title: String,
    images: List<Image>,
    addresses: List<Address>,
    price: Int,
    isOpen: Boolean?,
    time: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable { onClick() },
    ) {
        if (images.size >= 3)
            LargeItemContent(
                title = title,
                images = images,
                addresses = addresses,
                price = price,
                isOpen = isOpen,
                time = time
            )
        else
            SmallItemContent(
                title = title,
                image = images.first(),
                addresses = addresses,
                price = price,
                isOpen = isOpen,
                time = time
            )
    }
}

@Composable
private fun LargeItemContent(
    title: String,
    images: List<Image>,
    addresses: List<Address>,
    price: Int,
    isOpen: Boolean?,
    time: String
) {
    Column {
        ThreeImagesInRow(images = images)
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            Title(title = title)
            Addresses(addresses = addresses)
            if (price > 0)
                Price(price = price)
            if (isOpen != null)
                Schedule(isOpen = isOpen, time = time)
        }
    }
}

@Composable
private fun SmallItemContent(
    title: String,
    image: Image,
    addresses: List<Address>,
    price: Int,
    isOpen: Boolean?,
    time: String
) {
    val density = LocalDensity.current
    var size by remember { mutableStateOf(0.dp) }
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.image)
                .placeholder(R.drawable.baseline_no_photography_24)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(0.33f)
                .size(size)
                .onGloballyPositioned { with(density) { size = it.size.width.toDp() } }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(4.dp)
                .weight(0.66f)
        ) {
            Title(title = title)
            Addresses(addresses = addresses)
            if (price > 0)
                Price(price = price)
            if (isOpen != null)
                Schedule(isOpen = isOpen, time = time)
        }
    }
}


@Composable
private fun ThreeImagesInRow(images: List<Image>) {
    var imageSize by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Row {
        images.forEach { image ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.image)
                    .placeholder(R.drawable.baseline_no_photography_24)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.33f)
                    .height(imageSize)
                    .onGloballyPositioned { with(density) { imageSize = it.size.width.toDp() } }
            )
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp
    )
}

@Composable
private fun Addresses(addresses: List<Address>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = addresses.first().addressName,
            modifier = Modifier.padding(start = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp
        )
        if (addresses.size > 1)
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer) {
                Text(text = "+${addresses.size - 1}")
            }
    }
}

@Composable
private fun Price(price: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.Payments,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "Ср. чек: $price",
            modifier = Modifier.padding(start = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp
        )
        Icon(
            imageVector = Icons.Rounded.CurrencyRuble,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun Schedule(isOpen: Boolean, time: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.AccessTime,
            contentDescription = null,
            tint = if (isOpen) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
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
            color = if (isOpen) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
            lineHeight = 18.sp,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
@Preview
private fun SmallCardPreview() {
    VisitTobolskTheme {
        ItemCard(
            title = TempData.cafes[0].title,
            images = TempData.cafes[0].images,
            addresses = TempData.cafes[0].addresses,
            price = TempData.cafes[0].avgPrice,
            isOpen = true,
            time = "11:00"
        ) {}
    }
}

@Composable
@Preview
private fun LargeCardPreview() {
    VisitTobolskTheme {
        ItemCard(
            title = TempData.cafes[1].title,
            images = TempData.cafes[1].images,
            addresses = TempData.cafes[1].addresses,
            price = TempData.cafes[0].avgPrice,
            isOpen = true,
            time = "11:00"
        ) {}
    }
}