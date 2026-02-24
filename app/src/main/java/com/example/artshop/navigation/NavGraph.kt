package com.example.artshop.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.artshop.appstate.AppViewModel
import com.example.artshop.di.AppContainer
import com.example.artshop.ui.ui.CheckoutScreen
import com.example.artshop.ui.ui.DetailsScreen
import com.example.artshop.ui.ui.HomeScreen
import com.example.artshop.ui.ui.ListingsScreen
import com.example.artshop.ui.ui.PicturesScreen

@Composable
fun NavGraph(navController: NavHostController, appContainer: AppContainer) {
    NavHost(navController = navController, startDestination = "main") {
        navigation(startDestination = Routes.HOME, route = "main") {
            composable(Routes.HOME) {
                HomeScreen(navController, appContainer)
            }

            composable(
                route = Routes.LISTINGS,
                arguments = listOf(navArgument("groupName") { type = NavType.StringType })
            ) { backStackEntry ->
                val groupName = backStackEntry.arguments?.getString("groupName")
                if (groupName != null) {
                    Log.d("Navigation", "Received groupName: $groupName")
                    ListingsScreen(navController, groupName, appContainer)
                }
            }

            composable(
                route = Routes.PICS_ART,
                arguments = listOf(navArgument("artistId") { type = NavType.StringType })
            ) { backStackEntry ->
                val artistId = backStackEntry.arguments?.getString("artistId")
                Log.d("Navigation", "Received categoryName: $artistId")
                if (artistId != null) {
                    PicturesScreen(navController, "artist", artistId, appContainer)
                }
            }

            composable(
                route = Routes.PICS_CAT,
                arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")
                Log.d("Navigation", "Received categoryName: $categoryId")
                if (categoryId != null) {
                    PicturesScreen(navController, "category", categoryId, appContainer)
                }
            }

            composable(
                route = Routes.DETAILS,
                arguments = listOf(navArgument("photoId") { type = NavType.StringType })
            ) { backStackEntry ->
                val photoId = backStackEntry.arguments?.getString("photoId")
                DetailsScreen(navController, photoId, appContainer)
            }

            composable(
                route = Routes.CHECKOUT) {
                CheckoutScreen(navController, appContainer)
            }
        }
    }
}