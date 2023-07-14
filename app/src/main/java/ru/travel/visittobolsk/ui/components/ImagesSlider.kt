package ru.travel.visittobolsk.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.R
//import ru.travel.visittobolsk.R
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.ui.theme.VisitTobolskTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesSlider(images: List<Image>, modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val pagerState = rememberPagerState { images.size }
    var height by remember { mutableStateOf(0.dp) }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.animateContentSize()
        ) { page ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page].image)
                        .crossfade(true)
                        .placeholder(R.drawable.baseline_no_photography_24)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(height = height)
                        .clip(MaterialTheme.shapes.large)
                        .onGloballyPositioned {
                            with(density) { height = (it.size.width / 16 * 9).toDp() }
                        }
                )
                AnimatedVisibility(visible = images[page].name.isNotEmpty()) {
                    Text(
                        text = images[page].name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
        if (images.size > 1)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val scope = rememberCoroutineScope()
                repeat(images.size) {
                    val color =
                        if (pagerState.currentPage == it)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primaryContainer
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(color)
                            .size(8.dp)
                            .clickable {
                                scope.launch { pagerState.animateScrollToPage(it) }
                            }
                    )
                }
            }
    }
}

@Composable
@Preview
private fun ImagesSliderPreview() {
    VisitTobolskTheme {
        ImagesSlider(
            images = listOf(Image(0, "", "Image subtitle")),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}