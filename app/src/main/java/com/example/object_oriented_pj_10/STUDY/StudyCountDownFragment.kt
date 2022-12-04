package com.example.object_oriented_pj_10.STUDY

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.object_oriented_pj_10.databinding.FragmentStudyCountdownBinding


class StudyCountDownFragment : Fragment() {

    var binding: FragmentStudyCountdownBinding?=null

    lateinit var countDownTimer: CountDownTimer
    
    var timeRunning = false // timer 실행 여부
    var startState=false // start 버튼 작동 여부
    var time =0L // 초기 값
    var tempTime= 0L // 실행 시간


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyCountdownBinding.inflate(inflater)

        return binding?.root
    }

    // Study CountDown Timer 에서 보여지는 버튼 3가지에 따른 실행 함수 호출.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnStart?.setOnClickListener{
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

    // start 버튼이 눌렸을때 timer가 보여지게 끔 작동.
    private fun viewMode(mode:String){
        startState=true

        if (mode=="start"){
            binding?.settingLayout?.visibility= View.GONE
            binding?.timerLayout?.visibility= View.VISIBLE
        }
        else{
            binding?.settingLayout?.visibility= View.VISIBLE
            binding?.timerLayout?.visibility= View.GONE
        }
    }

    // timeRunning(boolean값) 에 따른 함수 실행 결정.
    private fun startStop(){
        if(timeRunning){
            stopTimer()
        }
        else{
            startTimer()
        }
    }

    // 시작 버튼 눌렀을때.
    private fun startTimer(){
        if (startState){
            val sHour= binding?.hourEdit?.text.toString()
            val sMin= binding?.minEdit?.text.toString()
            val sSec= binding?.secEdit?.text.toString()
            time =(sHour.toLong()*3600000)+(sMin.toLong()*60000)+(sSec.toLong()*1000)+1000
        }
        else{
            time = tempTime
        }
        countDownTimer= object: CountDownTimer(time,1000){
            override fun onTick(millisUnitFinshed: Long) {
                tempTime=millisUnitFinshed
                updateTime()
            }

            override fun onFinish() {}
        }.start()
        binding?.stopBtn?.text="일시정지"
        timeRunning=true
        startState=false
    }

    // 멈춤 버튼 눌렀을때.
    private fun stopTimer(){
        countDownTimer.cancel()
        timeRunning=false
        binding?.stopBtn?.text="계속"

    }

    // 시간을 입력했을때.
    private fun updateTime(){
        val hour = tempTime/3600000
        val min =tempTime%3600000/60000
        val sec=tempTime%3600000%60000/1000

        var timerLeftText="$hour :"
        if(min<10)timerLeftText+="0"
        timerLeftText+="$min :"
        if(sec<10)timerLeftText+="0"
        timerLeftText+="$sec"
        binding?.timerText?.text=timerLeftText
    }
}