package com.example.artshop.ui.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.artshop.R
import com.example.artshop.ui.viewmodels.ListingsViewModel
import com.example.artshop.ui.viewmodels.ListingsViewModelFactory
import com.example.artshop.ui.viewmodels.PicturesViewModel
import com.example.artshop.ui.viewmodels.PicturesViewModelFactory
import com.example.artshop.di.AppContainer
import com.example.artshop.models.Artist
import com.example.artshop.models.Category
import com.example.artshop.models.Photo



@Composable
fun PicturesScreen(navController: NavHostController, type: String, artCatId: String, appContainer: AppContainer) {
    val listingsViewModel: ListingsViewModel = viewModel(factory = ListingsViewModelFactory(appContainer))
    val picturesViewModel: PicturesViewModel = viewModel(factory = PicturesViewModelFactory(appContainer))

    Log.d("PicturesScreen", "categoryName: $artCatId")
    val categories by listingsViewModel.categories.collectAsState()
    val artists by listingsViewModel.artists.collectAsState()
    val photos by picturesViewModel.photos.collectAsState()
    val isLoading by picturesViewModel.isLoading.collectAsState()
    val error by picturesViewModel.error.collectAsState()


    if (isLoading) {
        // Show loading indicator
        Text("Loading...")
    } else if (error != null) {
        // Show error message
        Text("Error: $error")
    } else {// Check if a category with the given ID exists
        if (type == "category") {
            PictureCategoryScreen(navController, artCatId, photos, categories)
        } else {
            PictureArtistScreen(navController, artCatId, photos, artists)
        }
    }
}

@Composable
fun PictureCategoryScreen(
    navController: NavHostController,
    categoryId: String,
    photos: List<Photo>,
    categories: List<Category>
) {
    // Assuming Category is now a data class with an id property
    val photosInCategory = photos.filter { it.categoryId == categoryId }
    val category = categories.find { it.id == categoryId }

    Scaffold(
        topBar = {
            TopBar(
                navController,
                "${stringResource(R.string.PicIn)} ${category?.name}"
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PhotoGrid(navController, photos = photosInCategory)
        }
    }
}

@Composable
fun PictureArtistScreen(
    navController: NavHostController,
    artistId: String,
    photos: List<Photo>,
    artists: List<Artist>
) {
    val photosByArtist = photos.filter { it.artistId == artistId }
    val artist = artists.find { it.id == artistId }

    Scaffold(
        topBar = {
            TopBar(
                navController,
                "${stringResource(R.string.PicFrom)} ${artist?.lastName}"
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PhotoGrid(navController, photos = photosByArtist)
        }
    }
}

@Composable
fun PhotoGrid(navController: NavHostController, photos: List<Photo>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(navController, photo = photo)
        }
    }
}

@Composable
fun PhotoItem(navController: NavHostController, photo: Photo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            text = photo.title,
            textAlign = TextAlign.Center
        )
        Image(
            painter = rememberAsyncImagePainter(photo.imageUrl), // Use imageUrl
            contentDescription = photo.title,
            modifier = Modifier
                .size(180.dp)
                .clickable {
                    navController.navigate("details/${photo.id}") // Navigate to details
                },
            contentScale = ContentScale.Crop
        )
    }
}