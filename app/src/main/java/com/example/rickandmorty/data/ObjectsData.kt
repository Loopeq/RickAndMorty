package com.example.rickandmorty.data

import com.google.gson.annotations.SerializedName

class ObjectsData {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("upImageUri")
    var upImageUri: String = ""

    @SerializedName("lowImageUri")
    var lowImageUri: String = ""

    @SerializedName("description")
    var destription: String = ""

    @SerializedName("head1")
    var head1: String = ""

    @SerializedName("body1")
    var body1: String = ""

    @SerializedName("head2")
    var head2: String = ""

    @SerializedName("body2")
    var body2: String = ""

    @SerializedName("head3")
    var head3: String = ""

    @SerializedName("body3")
    var body3: String = ""
}

class SeriesObjectModel{
    @SerializedName("title")
    var title: String = ""
    var name: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("imageUri")
    var imageUri: String = ""
}


