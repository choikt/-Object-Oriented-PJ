package com.example.object_oriented_pj_10

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.object_oriented_pj_10.databinding.FragmentExerciseTimerBinding
import com.example.object_oriented_pj_10.repository.MyExerciseRepository
import java.util.ArrayList


class ExerciseTimer : Fragment() {

    var binding: FragmentExerciseTimerBinding?=null

    lateinit var countdown_timer : CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L;
    lateinit var name: TextView
    lateinit var timer: TextView
    private val repository = MyExerciseRepository();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseTimerBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val results = bundle!!.getParcelableArrayList<ExerciseList>("exercise")
        binding?.button?.setOnClickListener{
            startWorkout(results, 0);
        }
    }


    private fun startWorkout(exerciseList: ArrayList<ExerciseList>?, a: Int){
        println(exerciseList);
        if (exerciseList!!.isEmpty()){
            binding?.name?.setText("끝났습니다!")
            binding?.timer?.setText("끝났습니다!")
        }else{

            val set = exerciseList?.get(0)

            var time = set!!.exerciseTime;
            if (a==1){
                var time = set!!.restTime;
            }

            object: CountDownTimer(time*1000.toLong(),1000){
                override fun onTick(millisUntilFinished: Long) {
                    binding?.name?.setText(set!!.name)


                    if (a==0){
                        binding?.timer?.setText("조금만 힘내자!! " + "%01d".format(millisUntilFinished/1000 / 60)+":"+"%01d".format(millisUntilFinished/1000 % 60))
                    }
                    else {
                        binding?.timer?.setText("조금만 쉴게요~~ " + "%01d".format(millisUntilFinished/1000 / 60)+":"+"%01d".format(millisUntilFinished/1000 % 60));
                    }
                }
                override fun onFinish() {
                    if (a==1){
                        exerciseList.removeAt(0)
                        startWorkout(exerciseList,0)
                    }
                    else{

                        startWorkout(exerciseList,1);
                    }
                }
            }.start()
        }
    }
}