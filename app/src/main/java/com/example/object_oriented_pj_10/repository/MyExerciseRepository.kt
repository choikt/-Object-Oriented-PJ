package com.example.object_oriented_pj_10.repository

import com.example.object_oriented_pj_10.ExerciseList
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyExerciseRepository {
    val database = Firebase.database
    val exerciseRef = database.getReference("ExerciseLists")



    fun postMbti(exerciseList: ExerciseList){
        println(exerciseList)
        exerciseRef.push().setValue(exerciseList.name.toString())
    }
}
