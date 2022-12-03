package com.example.object_oriented_pj_10

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ExerciseList (
    val name: String?,
    val restTime : Int,
    val exerciseTime : Int
) : Parcelable