package com.example.rickandmorty.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.userinterface.BackGroundCompose
import com.example.rickandmorty.userinterface.LowTopBar
import com.example.rickandmorty.userinterface.getCurrentCharacter
import java.time.format.TextStyle

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldLowScreen(navController: NavController, name: String, data: ListObjectsModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            LowTopBar(name = name, navController = navController)
        },
        content = {
            LowerLevelScreen(name = name, data = data)
        }
    )
}


@Composable
fun LowerLevelScreen(name: String, data: ListObjectsModel){
    val textStyleH3 = MaterialTheme.typography.h3
    val textStyle by remember { mutableStateOf(textStyleH3) }

    BackGroundCompose()

    val objData = getCurrentCharacter(data, nameOfObject = name)
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = ScrollState(2))) {
        Box(modifier = Modifier
            .width(400.dp)
            .height(300.dp)){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(objData.upImageUri)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 25.dp, start = 30.dp, end = 30.dp)


            )
        }
        // главная часть
        Spacer(Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                objData.description, modifier = Modifier,
                style = textStyle,
                softWrap = true,

                )
        }

        //

        if (objData.head1.isNotEmpty()){
            DefaultBoxObject(head = objData.head1, body = objData.body1)
        }
        if (objData.head2.isNotEmpty()){
            DefaultBoxObject(head = objData.head2, body = objData.body2)
        }
        if (objData.head3.isNotEmpty()){
            DefaultBoxObject(head = objData.head3, body = objData.body3)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun DefaultBoxObject(head: String, body: String) {
    val textStyleH3 = MaterialTheme.typography.h3
    val textStyle by remember { mutableStateOf(textStyleH3) }
    Spacer(modifier = Modifier.height(25.dp))
    Text(head, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
    Spacer(modifier = Modifier.height(10.dp))
    Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(start = 5.dp, end = 5.dp))
    Spacer(modifier = Modifier.height(10.dp))
    Text(body, style = textStyle, modifier = Modifier.padding(start = 20.dp, end = 20.dp))

}



