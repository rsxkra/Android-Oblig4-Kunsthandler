package com.example.artshop.ui.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.artshop.data.entity.Basket
import com.example.artshop.appstate.BasketEvent
import com.example.artshop.R
import com.example.artshop.ui.viewmodels.BasketViewModel
import com.example.artshop.ui.viewmodels.BasketViewModelFactory
import com.example.artshop.di.AppContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, appContainer: AppContainer) {
    val basketViewModel: BasketViewModel = viewModel(factory = BasketViewModelFactory(appContainer))
    val basketUiState by basketViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.ArtDealer))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ))
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(text = stringResource(id = R.string.choices),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { navController.navigate("listings/Artists") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .width(150.dp)
                            .height(50.dp)) {
                        Log.d("Navigation", "Navigating to: Artists")
                        Text(text = stringResource(R.string.Artists),
                            style = MaterialTheme.typography.titleMedium)
                    }
                    Button(onClick = { navController.navigate("listings/Categories") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .width(150.dp)
                            .height(50.dp)) {
                        Text(text = stringResource(R.string.Categories),
                            style = MaterialTheme.typography.titleMedium)
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))



                Text(text = "${stringResource(R.string.TotalPics)} ${basketUiState.totalPics}",
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 16.dp))
                Text(text = "${stringResource(R.string.TotalPrice)} ${basketUiState.totalPrice}",
                    modifier = Modifier.padding(8.dp))
            }

            items(basketUiState.basket) { basketItem ->
                SelectedPhotoCard(basketItem = basketItem, basketViewModel = basketViewModel)
            }

            item{
                Divider(modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(top = 16.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))

                Button(onClick = { navController.navigate("checkout") },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(150.dp)
                        .height(50.dp)) {
                    Text(text = stringResource(R.string.Checkout),
                        style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun SelectedPhotoCard(basketItem: Basket, basketViewModel: BasketViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "${stringResource(R.string.FrameType)} ${basketItem.frameType}")
                Text(text = "${stringResource(R.string.FrameWidth)} ${basketItem.frameWidth}")
            }
            Column {
                Text(text = "${stringResource(R.string.PhotoSize)} ${basketItem.photoSize}")
                Text(text = "${stringResource(R.string.PhotoPrice)} ${basketItem.photoPrice}")
            }
            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = { basketViewModel.onEvent(BasketEvent.DeletePhoto(basketItem)) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                    )
                }
            }
        }
    }
}