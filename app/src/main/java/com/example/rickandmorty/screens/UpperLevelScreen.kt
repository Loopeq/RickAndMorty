package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import android.util.Log
import android.view.animation.BounceInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.MainViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.data.SearchData
import com.example.rickandmorty.data.SearchWidgetState
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.userinterface.*
import kotlin.collections.ArrayList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FrequentlyChangedStateReadInComposition")
@Composable
fun ScaffoldUpScreen(
    title: String, navController: NavController,
    data: ListObjectsModel,
    mainViewModel: MainViewModel
) {
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState
    val lazyGridState: LazyGridState = rememberLazyGridState()
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
            UpperLevelScreen(
                state = lazyGridState,
                navController = navController,
                data = listData,
                title = title,
                searchTextState
            )
        },


    )
}




@Composable
fun UpperLevelScreen(
    state: LazyGridState,
    navController: NavController,
    data: ArrayList<SearchData>,
    title: String,
    searchTextState: String
) {
    val listData = data
    var filteredData: ArrayList<SearchData>

    BackGroundCompose()

    val showButton by remember {
        derivedStateOf { state.firstVisibleItemIndex > 0 }
    }

    Box() {

        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
        ) {

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
            ) { _, item ->
                ObjectsBox(
                    navController = navController,
                    imageUri = item.imageUrl,
                    name = item.title,
                    title = title
                )
            }
        }
        AnimatedVisibility(
            visible = showButton, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 4.dp, bottom = 4.dp)
            , enter = fadeIn(animationSpec = tween(1000)) + expandHorizontally(
                animationSpec = tween(
                    1000
                ), expandFrom = Alignment.Start
            ),
            exit = fadeOut(animationSpec = tween(1000)) + shrinkHorizontally(
                animationSpec = tween(1000), shrinkTowards = Alignment.Start
            )
        ) {
            ScrollToTopItem(state)
        }
    }



}




@Composable
fun ObjectsBox(navController: NavController, imageUri: String, name: String, title: String) {
    val textStyleB1 = MaterialTheme.typography.body1
    val textStyle by remember { mutableStateOf(textStyleB1) }

    Surface(
        elevation = 4.dp, modifier = Modifier
            .padding(15.dp)
            .width(190.dp)
            .height(248.dp)
            .background(
                colorResource(id = R.color.black)
            )
    ) {
        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(colorResource(id = R.color.black))
                .clickable {
                    navController.navigate(Screen.LowerLevelScreen.route + "/${title}" + "/${name}")
                }
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = name,
                    style = textStyle,
                    maxLines = 2,
                    softWrap = true,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

