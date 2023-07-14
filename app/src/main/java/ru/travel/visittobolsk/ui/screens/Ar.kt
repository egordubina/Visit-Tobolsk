package ru.travel.visittobolsk.ui.screens
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.compose.animation.AnimatedContent
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.LinearProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.OutlinedCard
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.platform.ViewCompositionStrategy
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.fragment.app.Fragment
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.google.accompanist.themeadapter.material3.Mdc3Theme
//import com.google.ar.core.Config
//import io.github.sceneview.ar.ArSceneView
//import io.github.sceneview.ar.node.ArModelNode
//import org.koin.androidx.viewmodel.ext.android.viewModel
//import ru.travel.visittobolsk.R
//import ru.travel.visittobolsk.data.models.ArModel
//import ru.travel.visittobolsk.databinding.FragmentArBinding
//import ru.travel.visittobolsk.ui.uistates.ArUiState
//import ru.travel.visittobolsk.ui.viewmodels.ArViewModel
//
//class Ar : Fragment(R.layout.fragment__ar) {
//    private var _binding: FragmentArBinding? = null
//    private val binding get() = checkNotNull(_binding)
//    private val vm: ArViewModel by viewModel()
//    private lateinit var sceneView: ArSceneView
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentArBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.apply {
//            toolbar.apply {
//                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//                setContent {
//                    val state = vm.uiState.collectAsState()
//                    var showSettings by rememberSaveable { mutableStateOf(true) }
//                    AnimatedContent(state.value, label = "") {
//                        when (it) {
//                            is ArUiState.Content -> {
//                                Mdc3Theme {
//                                    CenterAlignedTopAppBar(
//                                        title = { Text(text = "AR") },
//                                        navigationIcon = {
//                                            IconButton(onClick = { /*findNavController().navigateUp()*/ }) {
//                                                Icon(
//                                                    painter = painterResource(id = R.drawable.deperound_arrow_back_ios_new_24),
//                                                    contentDescription = null
//                                                )
//                                            }
//                                        },
//                                        actions = {
//                                            IconButton(onClick = { showSettings = true }) {
//                                                Icon(
//                                                    painter = painterResource(id = R.drawable.round_settings_24),
//                                                    contentDescription = null
//                                                )
//                                            }
//                                        }
//                                    )
//                                    if (state.value == ArUiState.Loading)
//                                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
//                                    if (showSettings && state.value is ArUiState.Content)
//                                        Selector(
//                                            changeShow = { showSettings = it },
//                                            models = it.arModels
//                                        )
//                                }
//                                buttonTakePhoto.apply {
//                                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//                                    setContent {
//                                        Mdc3Theme {
//                                            Button(
//                                                onClick = { }, modifier = Modifier
//                                                    .fillMaxWidth()
//                                                    .padding(16.dp)
//                                            ) {
//                                                Icon(
//                                                    painter = painterResource(id = R.drawable.round_camera_enhance_24),
//                                                    contentDescription = null,
//                                                    modifier = Modifier.padding(end = 4.dp)
//                                                )
//                                                Text(text = "Сделать фото")
//                                            }
//                                        }
//                                    }
//                                }
//                                sceneView = arSceneView
//                                arSceneView.apply {
//                                    lightEstimationMode =
//                                        Config.LightEstimationMode.ENVIRONMENTAL_HDR
//                                    depthEnabled = true
//                                    instantPlacementEnabled = true
//                                }
//                            }
//
//                            ArUiState.Error -> {
//
//                            }
//
//                            ArUiState.Loading -> {
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    private fun Selector(
//        changeShow: (Boolean) -> Unit,
//        models: List<ArModel>
//    ) {
//        Log.e("Models", models.toString())
//        var imageHeight by remember { mutableStateOf(0.dp) }
//        val density = LocalDensity.current
//        ModalBottomSheet(onDismissRequest = { changeShow(false) }) {
//            Text(
//                text = "Выбери модель",
//                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp),
//                modifier = Modifier.padding(16.dp)
//            ) {
//                items(models) {
//                    OutlinedCard(
//                        onClick = {
//                            sceneView.children.forEach {
//                                sceneView.removeChild(it)
//                            }
//                            sceneView.addChild(
//                                ArModelNode(
//                                    engine = sceneView.engine,
//                                    modelGlbFileLocation = it.model
//                                )
//                            )
//                            changeShow(false)
//                        },
//                        modifier = Modifier.onGloballyPositioned {
//                            with(density) { imageHeight = it.size.width.toDp() }
//                        }
//                    ) {
//                        AsyncImage(
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data(it.image)
//                                .crossfade(true)
//                                .placeholder(R.drawable.baseline_no_photography_24)
//                                .build(),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.height(imageHeight).padding(8.dp)
//                        )
//                        Text(
//                            text = it.name,
//                            style = MaterialTheme.typography.labelLarge,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 8.dp)
//                                .padding(bottom = 8.dp)
//                        )
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}