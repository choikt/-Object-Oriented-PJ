package com.example.object_oriented_pj_10

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.object_oriented_pj_10.databinding.FragmentStudyBinding

class StudyFragment : Fragment() {
    var binding: FragmentStudyBinding?=null
    lateinit var countDownTimer: CountDownTimer


    var timeRunning = false
    var firstState=false
    var time =0L
    var tempTime= 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnFirst?.setOnClickListener{
            viewMode("start")
            startStop()
        }
        binding?.stopBtn?.setOnClickListener{
            startStop()
        }
        binding?.cancelBtn?.setOnClickListener{
            viewMode("cancel")
            stopTimer()
        }

    }




    private fun viewMode(mode:String){
        /*firstState=true

        if (mode=="start"){*/
            binding?.settingLayout?.visibility= View.GONE
            binding?.timerLayout?.visibility= View.VISIBLE
     /*   }
        else{
            binding?.settingLayout?.visibility= View.VISIBLE
            binding?.timerLayout?.visibility= View.GONE
        }*/
    }
    private fun startStop(){
        if(timeRunning){
            stopTimer()
        }
        else{
            startTimer()
        }
    }
    private fun startTimer(){
        if (firstState){
            val sHour= binding?.hourEdit?.text.toString()
            val sMin= binding?.minEdit?.text.toString()
            val sSec= binding?.secEdit?.text.toString()
            time =(sHour.toLong()*3600000)+(sMin.toLong()*60000)+(sSec.toLong()*1000)+1000
        }
        else{
            time = tempTime
        }
        countDownTimer= object:CountDownTimer(time,1000){
            override fun onTick(millisUnitFinshed: Long) {
                tempTime=millisUnitFinshed
                updateTime()
            }

            override fun onFinish() {}
        }.start()
        binding?.stopBtn?.text="일시정지"
        timeRunning=true
        firstState=false
    }
    private fun stopTimer(){
        countDownTimer.cancel()
        timeRunning=false
        binding?.stopBtn?.text="계속"

    }
    private fun updateTime(){
        val hour = tempTime/3600000
        val min =tempTime%3600000/60000
        val sec =tempTime%3600000%60000/1000

        var timerLeftText="$hour :"
        if(min<10)timerLeftText+="0"
        timerLeftText+="$min :"
        if(sec<10)timerLeftText+="0"
        timerLeftText+="$sec"
        binding?.timerText?.text=timerLeftText
    }
}