package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesObjectsModel
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.userinterface.BackGroundCompose
import com.example.rickandmorty.userinterface.LowTopBar
import com.example.rickandmorty.userinterface.getCurrentCharacter
import com.example.rickandmorty.userinterface.getCurrentSeries

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldSDescriptionScreen(navController: NavController, name: String, data: EpisodesObjectsModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            LowTopBar(name = name, navController = navController)
        },
        content = {
            SiriesDescriptionScreen(name = name, data = data)
        }
    )
}


@Composable
fun SiriesDescriptionScreen(name: String, data: EpisodesObjectsModel){
    BackGroundCompose()
    val objData = getCurrentSeries(json = data, nameOfObject = name)
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(10.dp))
        Text(
            objData.description, modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp),
            color = Color.White,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(20.dp))
    }
    Spacer(Modifier.height(15.dp))
}
