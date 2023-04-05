package com.example.rickandmorty

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import com.example.rickandmorty.data.EpisodesObjectsModel
import com.example.rickandmorty.data.ListObjectsModel
import com.example.rickandmorty.navigation.NavigationHost
import com.example.rickandmorty.userinterface.LoadJson
import com.example.rickandmortyLibary.ui.theme.RandMAppTheme

import com.google.gson.Gson


class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characters =
            Gson().fromJson(LoadJson("characters.json", this), ListObjectsModel::class.java)
        val objects =
            Gson().fromJson(LoadJson("objects.json", this), ListObjectsModel::class.java)
        val locations =
            Gson().fromJson(LoadJson("locations.json", this), ListObjectsModel::class.java)
        val epone =
            Gson().fromJson(LoadJson("episodone.json", this), EpisodesObjectsModel::class.java)
        val eptwo =
            Gson().fromJson(LoadJson("episodetwo.json", this), EpisodesObjectsModel::class.java)
        val epthree =
            Gson().fromJson(LoadJson("episodthree.json", this), EpisodesObjectsModel::class.java)
        val epfour =
            Gson().fromJson(LoadJson("episodfour.json", this), EpisodesObjectsModel::class.java)
        val epfive =
            Gson().fromJson(LoadJson("episodfive.json", this), EpisodesObjectsModel::class.java)
        val epsix =
            Gson().fromJson(LoadJson("episodsix.json", this), EpisodesObjectsModel::class.java)
        setContent {
            RandMAppTheme() {
                Surface {
                    NavigationHost(
                        context = this,
                        mainViewModel = mainViewModel,
                        characters = characters,
                        objects = objects,
                        locations = locations,
                        epone = epone,
                        eptwo = eptwo,
                        epthree = epthree,
                        epfour = epfour,
                        epfive = epfive,
                        epsix = epsix
                    )
                }
            }
        }
        }
    }




