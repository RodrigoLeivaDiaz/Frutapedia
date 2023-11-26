package com.example.frutapedia

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FruitsApi(
    val name : String,
    val id: Int,
    var image: Int,
    @SerializedName("nutritions") val nutricion: Nutritions

) : Parcelable

@Parcelize
data class Nutritions (
    val calories: Long,
    val fat: Double,
    val sugar: Double,
    val carbohydrates: Double,
    val protein: Double
) : Parcelable
