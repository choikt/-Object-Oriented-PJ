package com.example.object_oriented_pj_10

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.object_oriented_pj_10.databinding.FragmentExerciseTimerBinding


class ExerciseTimer : Fragment() {

    var binding: FragmentExerciseTimerBinding?=null

    lateinit var countdown_timer : CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L;
    lateinit var name: TextView
    lateinit var timer: TextView

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

        val result = bundle!!.getParcelableArrayList<ExerciseList>("exercise")


//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            val result = bundle.getSerializable("bundleKey");
//            println(result);
//        }

        var sets = result
        if (sets==null){
            name.setText(" ");
            timer.setText("조금만 힘내!!"+0);
        }else{
        for (set in sets){
            println(set);
        }
        binding?.button?.setOnClickListener{
            for (set in sets){
                object: CountDownTimer(set.exerciseTime*1000.toLong(),1000){
                    override fun onTick(millisUntilFinished: Long) {
                        name.setText(set.name)
                        timer.setText("조금만 힘내자!! " + millisUntilFinished / 1000)
                    }

                    override fun onFinish() {
                        name.setText("쉬는 시간")
                        timer.setText("0")
                    }
                }.start()
            }
        }
        }

    }

}