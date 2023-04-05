package com.example.rickandmorty.screens


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.data.HomeScreenData
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.userinterface.BackGroundCompose


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldHomeScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
           HomeScreen(navController = navController)
        }
    )
}



@Composable
fun HomeScreen(
    navController: NavController) {
    val textStyleB2 = MaterialTheme.typography.body2
    val textStyle by remember { mutableStateOf(textStyleB2) }
    val characterTitle = stringResource(id = R.string.characterstitle)
    val charactersImage = stringResource(id = R.string.charactersimage)
    val objectsTitle = stringResource(id = R.string.objectstitle)
    val objectsImage = stringResource(id = R.string.objectsimage)
    val locationTitle = stringResource(id = R.string.locationtitle)
    val locationImage = stringResource(id = R.string.locationimage)
    val episodesTitle = stringResource(id = R.string.episodestitle)
    val episodesImage = stringResource(id = R.string.episodesimage)

    BackGroundCompose()
    LazyColumn(
       modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight()
    ) {
        itemsIndexed(
            listOf(
                HomeScreenData(
                    title = characterTitle,
                    path = charactersImage
                ),
                HomeScreenData(
                    title = locationTitle,
                    path = locationImage
                ),
                HomeScreenData(
                    title = objectsTitle,
                    path = objectsImage
                ),
                HomeScreenData(
                    title = episodesTitle,
                    path = episodesImage
                )
            )) { _, item ->
            Surface(elevation = 4.dp,  modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillParentMaxHeight(0.35f)
                .background(colorResource(id = R.color.black))
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(colorResource(id = R.color.black))
                        .clickable {
                            navController.navigate(Screen.UpperLevelScreen.route + "/${item.title}")
                        }

                    ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.path)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)

                    )
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillParentMaxSize()) {
                        Text(
                            text = item.title,
                           style = textStyle,
                           )
                    }
                }
            }

        }
    }
}