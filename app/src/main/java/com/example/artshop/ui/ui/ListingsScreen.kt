package com.example.artshop.ui.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.artshop.R
import com.example.artshop.ui.viewmodels.ListingsViewModel
import com.example.artshop.ui.viewmodels.ListingsViewModelFactory
import com.example.artshop.di.AppContainer
import com.example.artshop.models.Artist
import com.example.artshop.models.Category
import java.util.Locale

@Composable
fun ListingsScreen(navController: NavHostController, groupName: String, appContainer: AppContainer) {
    val listingsViewModel: ListingsViewModel = viewModel(factory = ListingsViewModelFactory(appContainer))

    val artists by listingsViewModel.artists.collectAsState() // Observe the artists list
    val categories by listingsViewModel.categories.collectAsState() // Observe the categories listviewModel.categories.collectAsState() // Observe the categories list
    val isLoading by listingsViewModel.isLoading.collectAsState()
    val error by listingsViewModel.error.collectAsState()

    Scaffold(topBar = {
        TopBar(navController, if (groupName == "Artists") stringResource(R.string.Artists)
        else stringResource(R.string.Categories))
    }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Log.d("Navigation", "Navigating to: $groupName")

        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

        } else if (error != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Error: $error")
            }

        } else {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                if (groupName == "Artists") {
                    ArtistsScreen(navController, artists) // Pass the artists list
                } else if (groupName == "Categories") {
                    CategoriesScreen(navController, categories) // Pass the categories list
                }
            }
        }
    }
    }
}

@Composable
fun ArtistsScreen(navController: NavHostController, artists: List<Artist>) {
    artists.forEach { artist ->
        Text(
            text = "${artist.firstName} ${artist.lastName}",
            modifier = Modifier
                .clickable {
                    navController.navigate("pics/artist/${artist.id}")
                    Log.d("Navigation", "Navigating to: pictures/${artist.id}")
                }
                .padding(8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CategoriesScreen(navController: NavHostController, categories: List<Category>) {
    categories.forEach { category ->
        Text(
            text = category.name.lowercase().capitalize(locale = Locale.ROOT),
            modifier = Modifier
                .clickable {
                    navController.navigate("pics/category/${category.id}")
                }
                .padding(8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}