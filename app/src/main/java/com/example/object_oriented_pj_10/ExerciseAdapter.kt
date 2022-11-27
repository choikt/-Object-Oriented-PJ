package com.example.object_oriented_pj_10;

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.object_oriented_pj_10.databinding.ListExerciseBinding

class ExerciseAdapter(val exercises: List<ExerciseList>): RecyclerView.Adapter<ExerciseAdapter.Holder>() {

        //한 칸이 viewholder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
        val holder = Holder(binding)
        return holder
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(exercises[position])
        }

        override fun getItemCount()= exercises.size

class Holder(private val binding: ListExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseList){

        binding.setName.text = exercise.name
        binding.exerciseTime.text = ((exercise.exerciseTime)/60).toString()+" : "+((exercise.exerciseTime)%60).toString()
        binding.restTime.text = ((exercise.restTime)/60).toString()+" : "+((exercise.restTime)%60).toString()

        binding.root.setOnClickListener {
        Toast.makeText(binding.root.context,"운동이름: ${exercise.name} 운동시간: ${exercise.exerciseTime}\n 쉬는시간: ${exercise.restTime}\n", Toast.LENGTH_SHORT).show()
        }
        }
        }
        }