package com.example.rickandmorty.data

import com.google.gson.annotations.SerializedName

class ListObjectsModel {
    @SerializedName("data")
    var data: ArrayList<ObjectsData> = ArrayList()
}

class EpisodesObjectsModel{
    @SerializedName("episodes")
    var episodes: ArrayList<SeriesObjectModel> = ArrayList()
}




