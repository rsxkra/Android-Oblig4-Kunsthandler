package com.example.artshop

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.artshop.navigation.NavGraph
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.artshop.appstate.AppViewModel
import com.example.artshop.appstate.AppViewModelFactory
import com.example.artshop.ui.theme.ArtshopTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val initialOrientation = resources.configuration.orientation

        setContent {
            ArtshopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appContainer = (LocalContext.current.applicationContext as
                            AppApplication).container

                    val appViewModel: AppViewModel = viewModel(factory =
                    AppViewModelFactory(appContainer))

                    appViewModel.updateOrientation(initialOrientation)

                    NavGraph(
                        navController = rememberNavController(),
                        appContainer = appContainer)
                }
            }
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContent {
            val appContainer = (LocalContext.current.applicationContext as AppApplication).container
            val viewModel: AppViewModel = viewModel(factory = AppViewModelFactory(appContainer))
            viewModel.updateOrientation(newConfig.orientation)
        }
    }

}






