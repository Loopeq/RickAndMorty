package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesScreenData
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.userinterface.BackGroundCompose
import com.example.rickandmorty.userinterface.UpTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldEpisodesScreen(title: String, navController: NavController){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 UpTopBar(title = title, navController = navController, onSearchedClicked = {})
        },
        content = {
            EpisodesScreen(navController = navController)

        }
    )
}

@Composable
fun EpisodesScreen(navController: NavController){
    BackGroundCompose()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(
            listOf(EpisodesScreenData("1 СЕЗОН", imageUrl = "https://mdk.red/m/AqqD4D3A/540x629.jpg"),
                    EpisodesScreenData("2 СЕЗОН", imageUrl = "https://static.wikia.nocookie.net/rickandmorty/images/d/d5/Season2.jpg/revision/latest/scale-to-width-down/1200?cb=20200718092925"),
                EpisodesScreenData("3 СЕЗОН", imageUrl = "https://image.tmdb.org/t/p/w780/qJ7ILAEJFbBP3DzsTU2Va551iiP.jpg"),
                EpisodesScreenData("4 СЕЗОН", imageUrl = "https://images.sellbrite.com/production/152272/P6396-FR003/7299270f-dfe5-54a2-b793-1abefdbfa18c.jpg"),
                EpisodesScreenData("5 СЕЗОН", imageUrl = "https://www.film.ru/sites/default/files/movies/posters/3689114-1622516.jpg"),
                EpisodesScreenData("6 CЕЗОН", imageUrl = "https://sun9-38.userapi.com/impg/2_jksL3Uqao78tNBXgyLjRxQ9oQQye3YeZm7KQ/eN9DJ57JAiE.jpg?size=755x1133&quality=95&sign=8f7cfe4011b688adc0f3e4fcbd30f020&c_uniq_tag=1pYquHz4jP7ZIrj-ffwtQub7UjUeCPmFQy7tZB9FUqM&type=album")
            )
        ) { _, item ->
            Surface(elevation = 4.dp,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .fillParentMaxHeight(0.9f)
                    .background(Color.Black)
            ) {
                EpisodesObjectsBox(navController = navController, imageUri = item.imageUrl, title = item.title)
             }
        }
    }

}


@Composable
fun EpisodesObjectsBox(navController: NavController, imageUri: String, title: String){
    val textStyleB1 = MaterialTheme.typography.body1
    val textStyle by remember { mutableStateOf(textStyleB1) }

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(id = R.color.black))
            .clickable {
                navController.navigate(Screen.SeriesScreen.route + "/${title}")
            }

    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                style = textStyle,
                maxLines = 1,
                softWrap = false,
                textAlign = TextAlign.Center
            )
        }
    }
}
