

package com.example.rickandmorty.userinterface

import android.widget.ImageButton
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.rickandmorty.R
import com.example.rickandmorty.data.SearchWidgetState
import com.example.rickandmortyLibary.ui.theme.PixelFont
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChanged: (String) -> Unit,
    onClosedClicked: () -> Unit,
    onSearchedClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    title: String,
    navController: NavController
){
    when(searchWidgetState){
        SearchWidgetState.CLOSED -> {
            UpTopBar(title = title, navController = navController, onSearchedClicked = onSearchTriggered)
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChanged,
                onClosedClicked = onClosedClicked,
                onSearchedClicked = onSearchedClicked
            )
        }
    }

}

@Composable
fun UpTopBar(title: String, navController: NavController, onSearchedClicked: () -> Unit) {
    val textStyleB2 = MaterialTheme.typography.body2
    val textStyle by remember { mutableStateOf(textStyleB2) }

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(title, style = textStyle)
            }
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.backarrow),
                    contentDescription = "back",
                    modifier = Modifier.scale(1.3f)
                )
            }

        },
        actions = {
            Image(painter = painterResource(id = R.drawable.search), contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onSearchedClicked()
                    }
                    .scale(scaleX = 0.6f, scaleY = 0.6f))
        }
    )
}

@Composable
fun SearchAppBar(
    text: String, onTextChange: (String) -> Unit, onClosedClicked: () -> Unit,
    onSearchedClicked: (String) -> Unit
) {
    val textStyleH3 = MaterialTheme.typography.h3
    val textStyle by remember { mutableStateOf(textStyleH3) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
            TextField(modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {
                onTextChange(it)
            }, textStyle = LocalTextStyle.current.copy(fontSize = 15.sp)
                ,placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Поиск..."
                )
            },
                singleLine = true,
                leadingIcon = {
                    IconButton(modifier = Modifier.alpha(ContentAlpha.medium),
                        onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    }
                },
                trailingIcon = {
                    IconButton(modifier = Modifier.alpha(ContentAlpha.medium),
                        onClick = {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onClosedClicked()
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.White
                        )
                    }
                }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchedClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.black),
                    cursorColor = Color.White
                )
            )
        }

    }


@Composable
fun LowTopBar(name: String, navController: NavController){
    val textStyleB2 = MaterialTheme.typography.body2
    val textStyle by remember { mutableStateOf(textStyleB2) }
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(name, style = textStyle, color = Color.White)
            }
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.backarrow),
                    contentDescription = "back",
                    modifier = Modifier.scale(1.3f),
                    tint = Color.White
                )
            }
        }
    )
}



@Composable
fun BackGroundCompose(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.home_brush_1),
                        colorResource(R.color.home_brush_2)
                    )
                )
            )
    )
}

@Composable
fun ScrollToTopItem(state: LazyGridState) {
    val composableScope = rememberCoroutineScope()

    FloatingActionButton(
        onClick = {
            composableScope.launch {
                state.animateScrollToItem(index = 0)
            }
        },
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 0)),
        backgroundColor = colorResource(id = R.color.black),
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrowup),
            contentDescription = null,
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
    }

}
