package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesObjectsModel
import com.example.rickandmorty.data.EpisodesScreenData
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.userinterface.BackGroundCompose
import com.example.rickandmorty.userinterface.UpTopBar
import java.text.CollationKey


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldSeriesScreen(title: String, navController: NavController, data: EpisodesObjectsModel){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            UpTopBar(title = title, navController = navController, onSearchedClicked = {})
        },
        content = {
            SeriesScreen(navController = navController, data = data)
        }
    )
}

@Composable
fun SeriesScreen(navController: NavController, data: EpisodesObjectsModel){
    BackGroundCompose()
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(
            data.episodes
        ){
            _, item ->
            Surface(elevation = 4.dp,  modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillParentMaxHeight(0.5f)
                .background(colorResource(id = R.color.black))
            ) {
                SeriesObjectsBox(
                    navController = navController,
                    imageUri = item.imageUri,
                    title = item.title,
                    name = item.name
                )
            }
        }
    }

}


@Composable
fun SeriesObjectsBox(navController: NavController, imageUri: String, title: String, name: String){
    val textStyleH4 = MaterialTheme.typography.h4
    val textStyle by remember { mutableStateOf(textStyleH4) }

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Black)
            .clickable {
                navController.navigate(Screen.SeriesDescriptionScreen.route + "/${title}" + "/${name}")
            }

    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        )
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                style = textStyle,
                textAlign = TextAlign.Center,
                )
            Text(
                text = name,
                style = textStyle,
                textAlign = TextAlign.Center
            )
        }

    }
}