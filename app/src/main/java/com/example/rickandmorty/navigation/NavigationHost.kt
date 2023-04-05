package com.example.rickandmorty.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.MainViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesObjectsModel
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.screens.*
import com.example.rickandmorty.userinterface.getCurrentEpisodesJson
import com.example.rickandmorty.userinterface.getCurrentJson
import com.example.rickandmorty.userinterface.getCurrentSeries


sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object UpperLevelScreen : Screen("uplevel_screen")
    object LowerLevelScreen : Screen("low_level_screen")
    object SeriesScreen: Screen("series_screen")
    object SeriesDescriptionScreen: Screen("series_description_screen")
    object LocationsScreen: Screen("location_screen")
}


@Composable
fun NavigationHost(
    mainViewModel: MainViewModel,
    context: Context,
    characters: ListObjectsModel,
    objects: ListObjectsModel,
    locations: ListObjectsModel,
    epone: EpisodesObjectsModel,
    eptwo: EpisodesObjectsModel,
    epthree: EpisodesObjectsModel,
    epfour: EpisodesObjectsModel,
    epfive: EpisodesObjectsModel,
    epsix: EpisodesObjectsModel
) {
    val episodesTitle = stringResource(id = R.string.episodestitle)
    val locationTitle = stringResource(id = R.string.locationtitle)
    val objectsTitle = stringResource(id = R.string.objectstitle)
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            ScaffoldHomeScreen(navController = navController)
        }

        composable(
            Screen.UpperLevelScreen.route + "/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val arg = backStackEntry.arguments?.getString("title")!!
            val data: ListObjectsModel = getCurrentJson(
                arg,
                characters = characters, objects = objects, context = context, locations = locations)
            when(arg){
                episodesTitle -> {
                    ScaffoldEpisodesScreen(title = arg, navController = navController)
                }
                locationTitle -> {
                    ScaffoldLocationsScreen(title = arg, navController = navController, data = locations, mainViewModel = mainViewModel)
                }
                objectsTitle -> {
                    ScaffoldLocationsScreen(title = arg, navController = navController, data = objects, mainViewModel = mainViewModel)
                }

                else -> {
                    ScaffoldUpScreen(
                        navController = navController,
                        data = data,
                        title = arg,
                        mainViewModel = mainViewModel
                    )}
            }

        }

        composable(
            Screen.LowerLevelScreen.route + "/{title}" + "/{name}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val argName = backStackEntry.arguments?.getString("name")!!
            val argTitle = backStackEntry.arguments?.getString("title")!!
            val data: ListObjectsModel = getCurrentJson(
                argTitle, locations = locations, characters = characters, objects = objects, context = context
            )
            ScaffoldLowScreen(navController = navController, name = argName, data = data)
        }

        composable(
            Screen.SeriesScreen.route + "/{title}",
            arguments = listOf(
                navArgument("title"){type = NavType.StringType}
            )
        ){ backStackEntry ->
            val argTitle = backStackEntry.arguments?.getString("title")!!
            val data: EpisodesObjectsModel = getCurrentEpisodesJson(epone = epone, eptwo = eptwo,
                epthree = epthree, epfour = epfour, epfive = epfive, epsix = epsix,
                nameOfObject = argTitle)
            ScaffoldSeriesScreen(title = argTitle, navController = navController, data = data)
        }

        composable(
            Screen.SeriesDescriptionScreen.route +"/{title}" + "/{name}",
            arguments = listOf(
                navArgument("title") {type = NavType.StringType},
                navArgument("name") {type = NavType.StringType}
            )
        ){ backStackEntry ->
            val argName = backStackEntry.arguments?.getString("name")!!
            val argTitle = backStackEntry.arguments?.getString("title")!!
            val data: EpisodesObjectsModel = getCurrentEpisodesJson(
                epone = epone, eptwo = eptwo,
                epthree = epthree, epfour = epfour, epfive = epfive, epsix = epsix,
                nameOfObject = argTitle
            )
            ScaffoldSDescriptionScreen(navController = navController, name = argName, data = data)
        }
    }

    }





