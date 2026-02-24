package com.example.artshop.ui.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.artshop.data.entity.Basket
import com.example.artshop.appstate.BasketEvent
import com.example.artshop.R
import com.example.artshop.appstate.DetailsEvent
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.PhotoSize
import com.example.artshop.ui.viewmodels.BasketViewModel
import com.example.artshop.ui.viewmodels.BasketViewModelFactory
import com.example.artshop.ui.viewmodels.DetailsViewModel
import com.example.artshop.ui.viewmodels.DetailsViewModelFactory
import com.example.artshop.utils.calculatePrice
import com.example.artshop.utils.createSelectedPhoto
import com.example.artshop.di.AppContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, photoId: String?, appContainer: AppContainer) {

    val basketViewModel: BasketViewModel = viewModel(factory = BasketViewModelFactory(appContainer))
    val detailsViewModel: DetailsViewModel = viewModel(factory = DetailsViewModelFactory(appContainer))

    /*
    val photo by detailsViewModel.photo.collectAsState()
    val isLoading by detailsViewModel.isLoading.collectAsState()
    val error by detailsViewModel.error.collectAsState()

    val frameTypes by detailsViewModel.frameTypes.collectAsState()
    val frameWidths by detailsViewModel.frameWidths.collectAsState()
    val photoSizes by detailsViewModel.photoSizes.collectAsState()

    val selectedFrameType by detailsViewModel.selectedFrameType.collectAsState()
    val selectedFrameWidth by detailsViewModel.selectedFrameWidth.collectAsState()
    val selectedPhotoSize by detailsViewModel.selectedPhotoSize.collectAsState()

    val showFrameTypeDropdown by detailsViewModel.showFrameTypeDropdown.collectAsState()
    val showFrameWidthDropdown by detailsViewModel.showFrameWidthDropdown.collectAsState()
    val showPhotoSizeDropdown by detailsViewModel.showPhotoSizeDropdown.collectAsState()

    var showMissingOptionsDialog by remember { mutableStateOf(false) }

    Log.d("DetailsScreen", "Frame Widths: $frameWidths")
    Log.d("DetailsScreen", "Selected Frame Width: $selectedFrameWidth")

     */
    val uiState by detailsViewModel.uiState.collectAsState()


    LaunchedEffect(photoId) {
        if (photoId != null) {
            detailsViewModel.onEvent(DetailsEvent.LoadPhotoDetails(photoId))
        }
    }
        Scaffold(topBar = {
            TopBar(navController, stringResource(R.string.Details))
        }) { innerPadding ->
            if (uiState.isLoading) {
                // Show loading indicator
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                // Show error message
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: $uiState.error")
                }
            } else if (uiState.photo != null && uiState.frameTypes.isNotEmpty()
                && uiState.frameWidths.isNotEmpty() && uiState.photoSizes.isNotEmpty()) {
                // Display photo details
                val price = calculatePrice(
                    uiState.photo!!,
                    uiState.selectedFrameType ?: uiState.frameTypes.firstOrNull() ?:
                    FrameType(id = "1", name = "Default", color = "0xFF80F0", extraPrice = 0.0), // Use first or default
                    uiState.selectedFrameWidth ?: uiState.frameWidths.firstOrNull() ?:
                    FrameWidth(id = "1", name = "Default", width = 10, extraPrice = 0.0),
                    uiState.selectedPhotoSize ?: uiState.photoSizes.firstOrNull() ?:
                    PhotoSize(id = "1", name = "Default", size = 170, extraPrice = 0.0) // Use first or default
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(uiState.photo!!.imageUrl), // Load image from URL
                                contentDescription = uiState.photo!!.title,
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(top = 8.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // ... (Rest of your UI for displaying details and dropdowns, adapted to use fetched data)
                            // Example:
                            Text(text = stringResource(R.string.FrameType))
                            ExposedDropdownMenuBox(
                                expanded = uiState.showFrameTypeDropdown,
                                onExpandedChange = {
                                    detailsViewModel.onEvent(DetailsEvent.ToggleFrameTypeDropdown)
                                }) {
                                TextField(
                                    modifier = Modifier.menuAnchor(),
                                    value = uiState.selectedFrameType?.name ?: "Select Frame Type",
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = uiState.showFrameTypeDropdown
                                        )
                                    }
                                )
                                ExposedDropdownMenu(
                                    expanded = uiState.showFrameTypeDropdown,
                                    onDismissRequest = { detailsViewModel.onEvent(DetailsEvent.ToggleFrameTypeDropdown) }) {
                                    uiState.frameTypes.forEach { frameType ->
                                        DropdownMenuItem(
                                            text = { Text(text = frameType!!.name) },
                                            onClick = {
                                                detailsViewModel.onEvent(DetailsEvent.UpdateSelectedFrameType(frameType))
                                                detailsViewModel.onEvent(DetailsEvent.ToggleFrameTypeDropdown)
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                        )
                                    }
                                }
                            }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = stringResource(R.string.FrameWidth))
                        ExposedDropdownMenuBox(
                            expanded = uiState.showFrameWidthDropdown,
                            onExpandedChange = {
                                detailsViewModel.onEvent(DetailsEvent.ToggleFrameWidthDropdown)
                            }) {
                            TextField(
                                modifier = Modifier.menuAnchor(),
                                value = uiState.selectedFrameWidth?.name ?: "Select Width",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = uiState.showFrameWidthDropdown
                                    )
                                }
                            )
                            ExposedDropdownMenu(
                                expanded = uiState.showFrameWidthDropdown,
                                onDismissRequest = {
                                    detailsViewModel.onEvent(DetailsEvent.ToggleFrameWidthDropdown)
                                }) {
                                uiState.frameWidths.forEach { frameWidth ->
                                    DropdownMenuItem(
                                        text = { Text(text = frameWidth!!.name) },
                                        onClick = {
                                            detailsViewModel.onEvent(DetailsEvent.UpdateSelectedFrameWidth(frameWidth))
                                            detailsViewModel.onEvent(DetailsEvent.ToggleFrameWidthDropdown)
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = stringResource(R.string.PhotoSize))
                        ExposedDropdownMenuBox(
                            expanded = uiState.showPhotoSizeDropdown,
                            onExpandedChange = {
                                detailsViewModel.onEvent(DetailsEvent.TogglePhotoSizeDropdown)
                            }) {
                            TextField(
                                modifier = Modifier.menuAnchor(),
                                value = uiState.selectedPhotoSize?.name ?: "Select Photo Size",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = uiState.showPhotoSizeDropdown
                                    )
                                }
                            )
                            ExposedDropdownMenu(
                                expanded = uiState.showPhotoSizeDropdown,
                                onDismissRequest = {
                                    detailsViewModel.onEvent(DetailsEvent.TogglePhotoSizeDropdown)
                                }) {
                                uiState.photoSizes.forEach { photoSize ->
                                    DropdownMenuItem(
                                        text = { Text(text = photoSize!!.name) },
                                        onClick = {
                                            detailsViewModel.onEvent(DetailsEvent.UpdateSelectedPhotoSize(photoSize))
                                            detailsViewModel.onEvent(DetailsEvent.TogglePhotoSizeDropdown)
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${stringResource(R.string.PhotoPrice)} $price",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                            var showMissingOptionsDialog by remember { mutableStateOf(false) }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            if (showMissingOptionsDialog) {
                                AlertDialog(
                                    onDismissRequest = { showMissingOptionsDialog = false },
                                    title = { Text(text = "Missing Options") },
                                    text = { Text(text = "Please select all options.") },
                                    confirmButton = {
                                        TextButton(onClick = { showMissingOptionsDialog = false }) {
                                            Text(text = "OK")
                                        }
                                    }
                                )
                            }
                            Button(
                                onClick = {
                                    if (uiState.selectedFrameType == null || uiState.selectedFrameWidth == null || uiState.selectedPhotoSize == null) {
                                        showMissingOptionsDialog = true
                                    } else {
                                        val selectedPhoto = createSelectedPhoto(
                                            uiState.photo,
                                            uiState.selectedFrameType,
                                            uiState.selectedFrameWidth,
                                            uiState.selectedPhotoSize
                                        )
                                        val basket = Basket(
                                            photoId = selectedPhoto.photoId,
                                            frameType = uiState.selectedFrameType!!.name,
                                            frameWidth = uiState.selectedFrameWidth!!.name,
                                            photoSize = uiState.selectedPhotoSize!!.name,
                                            photoPrice = price
                                        )
                                        basketViewModel.onEvent(BasketEvent.SavePhoto(basket))
                                        navController.navigate("home")
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .width(150.dp)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.AddToBasket),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            Button(
                                onClick = { navController.navigate("Home") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .width(150.dp)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.Home),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}