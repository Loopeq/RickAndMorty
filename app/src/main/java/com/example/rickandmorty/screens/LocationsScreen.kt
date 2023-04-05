package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.MainViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesObjectsModel
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.data.SearchData
import com.example.rickandmorty.data.SearchWidgetState
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.userinterface.BackGroundCompose
import com.example.rickandmorty.userinterface.MainTopBar
import com.example.rickandmorty.userinterface.UpTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldLocationsScreen(title: String, navController: NavController, data: ListObjectsModel, mainViewModel: MainViewModel){
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val listData = ArrayList<SearchData>()
    for(obj in data.data){
        listData.add(SearchData(obj.name, obj.upImageUri))
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChanged = {mainViewModel.updateSearchTextState(newValue = it)},
                onClosedClicked = {
                    mainViewModel.updateSearchTextState(newValue = "")
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchedClicked = {
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                title = title,
                navController = navController
            )
        },
        content = {
            LocationsScreen(navController = navController, data = listData, title = title, searchTextState)
        }
    )
}




@Composable
fun LocationsScreen(navController: NavController, data:  ArrayList<SearchData>, title: String, searchTextState: String){
    val listData = data
    var filteredData: ArrayList<SearchData>

    BackGroundCompose()
    LazyColumn(modifier = Modifier.fillMaxSize()){

        filteredData = if (searchTextState.isEmpty()) {
            listData
        } else {
            val resultData = ArrayList<SearchData>()
            val iterator = listData.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.title.lowercase().contains(searchTextState.lowercase())) {
                    resultData.add(SearchData(item.title, item.imageUrl))
                }
            }
            resultData
        }

        itemsIndexed(
            filteredData
        ){
                _, item ->
            Surface(elevation = 4.dp,  modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillParentMaxHeight(0.5f)
                .background(colorResource(id = R.color.black))
            ) {
                LocationsObjectsBox(
                    navController = navController,
                    imageUri = item.imageUrl,
                    title = title,
                    name = item.title
                )
            }
        }
}
    }

@Composable
fun LocationsObjectsBox(navController: NavController, imageUri: String, title: String, name: String){
    val textStyleB1 = MaterialTheme.typography.body1
    val textStyle by remember { mutableStateOf(textStyleB1) }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(colorResource(id = R.color.black))
                .clickable {
                    navController.navigate(Screen.LowerLevelScreen.route + "/${title}" + "/${name}")
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
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = name,
                    style = textStyle,
                    textAlign = TextAlign.Center)
            }
        }
    }


