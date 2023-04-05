package com.example.rickandmorty.data

data class HomeScreenData(val title: String, val path: String)
data class UpperScreenData(
    val name: String,
    val upImageUri: String,
    val description: String,
    val head1: String,
    val body1: String,
    val head2: String,
    val body2: String,
    val head3: String,
    val body3: String
)
data class EpisodesScreenData(
    val title: String,
    val imageUrl: String
)

data class SeriesScreenData(
    val title: String,
    val name: String,
    val description: String,
    val imageUri: String
)

data class SearchData(
    val title: String,
    val imageUrl: String
)

