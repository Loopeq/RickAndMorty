package com.example.rickandmorty.userinterface

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.remember
import com.example.rickandmorty.R
import com.example.rickandmorty.data.*
import com.example.rickandmorty.navigation.Screen
import java.io.InputStream
import androidx.compose.ui.res.stringResource as stringResource1


fun LoadJson(path: String, context: Context): String? {
    var input: InputStream? = null
    val jsonString: String

    try{
        input = context.assets.open(path)
        val size = input.available()
        val buffer = ByteArray(size)
        input.read(buffer)
        jsonString = String(buffer)
        return jsonString
    } catch (ex: java.lang.Exception){
        ex.printStackTrace()
    } finally {
        input?.close()
    }
    return null
}


fun getCurrentJson(
    name: String,
    characters: ListObjectsModel,
    objects: ListObjectsModel,
    locations: ListObjectsModel,
    context: Context
): ListObjectsModel {
    var result: ListObjectsModel = characters
    when(name){
        "Герои" -> result = characters
        "Объекты" -> result = objects
        "Локации" -> result = locations
    }
    return result
}

fun getCurrentCharacter(json: ListObjectsModel, nameOfObject: String): UpperScreenData {
    var result = UpperScreenData(name = "default", upImageUri = "default", description = "default",
    head1 = "default", body1 = "default", head2 = "default", body2 = "default", head3 = "default", body3 = "default")
    for(obj in json.data){
        if (obj.name == nameOfObject){
            result = UpperScreenData(
                name = obj.name,
                upImageUri = obj.upImageUri,
                description = obj.destription,
                head1 = obj.head1,
                body1 = obj.body1,
                head2 = obj.head2,
                body2 = obj.body2,
                head3 = obj.head3,
                body3 = obj.body3
            )
        }
    }
    return result
}

fun getCurrentEpisodesJson(
    epone: EpisodesObjectsModel,
    eptwo: EpisodesObjectsModel,
    epthree: EpisodesObjectsModel,
    epfour: EpisodesObjectsModel,
    epfive: EpisodesObjectsModel,
    epsix: EpisodesObjectsModel,
    nameOfObject: String
): EpisodesObjectsModel {
    var result: EpisodesObjectsModel = epone
    when(nameOfObject){
        "1 СЕЗОН" ->
            result = epone
        "2 СЕЗОН" ->
            result = eptwo
        "3 СЕЗОН" -> result = epthree
        "4 СЕЗОН" -> result = epfour
        "5 СЕЗОН" -> result = epfive
        "6 СЕЗОН" -> result = epsix
    }
    return result
}

fun getCurrentSeries(json: EpisodesObjectsModel, nameOfObject: String): SeriesScreenData{
    var result = SeriesScreenData(title = "default", name = "default", description = "default", imageUri = "default")
    for(obj in json.episodes){
        if(obj.name == nameOfObject){
            result = SeriesScreenData(name = obj.name, title = obj.title, description = obj.description, imageUri = obj.imageUri)

        }
    }
    return result
}