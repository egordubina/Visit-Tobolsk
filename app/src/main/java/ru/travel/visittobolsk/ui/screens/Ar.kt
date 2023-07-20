package ru.travel.visittobolsk.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.ar.core.Config
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Scale
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.R
import ru.travel.visittobolsk.data.models.ArModel
import ru.travel.visittobolsk.ui.uistates.ArUiState
import ru.travel.visittobolsk.ui.viewmodels.ArViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArScreen(
    vm: ArViewModel,
    state: ArUiState,
    onBackButtonClick: () -> Unit,
) {
    val context = LocalContext.current
    var showSettings by rememberSaveable { mutableStateOf(true) }
    val selectorState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val nodes = remember { mutableStateListOf<ArNode>() }
    var sceneView by remember { mutableStateOf<ArSceneView?>(null) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "AR") },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showSettings = true }) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ARScene(
                modifier = Modifier.fillMaxSize(),
                nodes = nodes,
                planeRenderer = true,
                onCreate = { scene ->
                    sceneView = scene
                    scene.configureSession { session, config ->
                        config.apply {
//                            focusMode = Config.FocusMode.FIXED
//                            instantPlacementMode = Config.InstantPlacementMode.DISABLED
//                            planeFindingMode = Config.PlaneFindingMode.DISABLED
                            lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
                        }
                    }
                },
                onSessionCreate = {

                },
                onFrame = {

                },
                onTap = {

                }
            )
        }
        if (showSettings && state is ArUiState.Content && sceneView != null)
            Selector(
                state = selectorState,
                closeSelector = {
                    scope.launch {
                        selectorState.hide()
                        showSettings = false
                    }
                },
                models = state.arModels,
                onModelClick = {
                    if (sceneView != null && nodes.isNotEmpty()) {
                        nodes.clear()
                        sceneView!!.children.forEach {
                            sceneView!!.removeChild(it)
                        }
                    }
                    try {
                        nodes.add(
                            ArNode(engine = sceneView!!.engine)
                                .loadModelGlbAsync(
                                    glbFileLocation = it,
                                    autoAnimate = true,
                                    centerOrigin = Position(z = 3f)
                                ) as ArNode
                        )
                    } catch (e: Exception) {
                        Log.e("AR Exception", e.toString())
                        Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                    }
                }
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Selector(
    state: SheetState,
    closeSelector: () -> Unit,
    models: List<ArModel>,
    onModelClick: (link: String) -> Unit
) {
    Log.e("Models", models.toString())
    var imageHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    ModalBottomSheet(onDismissRequest = closeSelector, sheetState = state) {
        Text(
            text = "Выбери модель",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(models) {
                OutlinedCard(
                    onClick = {
                        onModelClick(it.model)
                        closeSelector()
                    },
                    modifier = Modifier.onGloballyPositioned {
                        with(density) { imageHeight = it.size.width.toDp() }
                    }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.image)
                            .crossfade(true)
                            .placeholder(R.drawable.baseline_no_photography_24)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(imageHeight)
                            .padding(8.dp)
                    )
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .padding(bottom = 8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}