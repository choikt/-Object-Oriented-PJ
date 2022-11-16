package com.example.object_oriented_pj_10

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.object_oriented_pj_10.databinding.FragmentCookBinding




class CookFragment : Fragment() {
    var binding: FragmentCookBinding?=null
    lateinit var countDownTimer: CountDownTimer
    lateinit var countDownTimer2: CountDownTimer
    lateinit var countDownTimer3: CountDownTimer



    var timeRunning = false
    var firstState=false

    var time =0L
    var tempTime= 0L
    var timeRunning2 = false
    var firstState2=false

    var time2 =0L
    var tempTime2= 0L
    var timeRunning3 = false
    var firstState3=false

    var time3 =0L
    var tempTime3= 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCookBinding.inflate(inflater)
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
        binding?.btnSecond?.setOnClickListener{
            viewMode2("start2")
            startStop2()
        }
        binding?.stopBtn2?.setOnClickListener{
            startStop2()
        }
        binding?.cancelBtn2?.setOnClickListener{
            viewMode2("cancel2")
            stopTimer2()
        }
        binding?.btnThird?.setOnClickListener{
            viewMode3("start3")
            startStop3()
        }
        binding?.stopBtn3?.setOnClickListener{
            startStop3()
        }
        binding?.cancelBtn3?.setOnClickListener{
            viewMode3("cancel3")
            stopTimer3()
        }

    }
    private fun viewMode3(mode:String){
        firstState3=true

        if (mode=="start3"){
            binding?.settingLayout3?.visibility= View.GONE
            binding?.timerLayout3?.visibility= View.VISIBLE
        }
        else{
            binding?.settingLayout3?.visibility= View.VISIBLE
            binding?.timerLayout3?.visibility= View.GONE
        }
    }
    private fun startStop3(){
        if(timeRunning3){
            stopTimer3()
        }
        else{
            startTimer3()
        }
    }
    private fun startTimer3(){
        if (firstState3){
            val sHour= binding?.hourEdit3?.text.toString()
            val sMin= binding?.minEdit3?.text.toString()
            val sSec= binding?.secEdit3?.text.toString()
            time3 =(sHour.toLong()*3600000)+(sMin.toLong()*60000)+(sSec.toLong()*1000)+1000
        }
        else{
            time3 = tempTime3
        }
        countDownTimer3= object:CountDownTimer(time3,1000){
            override fun onTick(millisUnitFinshed: Long) {
                tempTime3=millisUnitFinshed
                updateTime3()
            }

            override fun onFinish() {}
        }.start()
        binding?.stopBtn3?.text="일시정지"
        timeRunning3=true
        firstState3=false
    }
    private fun stopTimer3(){
        countDownTimer3.cancel()
        timeRunning3=false
        binding?.stopBtn3?.text="계속"

    }
    private fun updateTime3(){
        val hour = tempTime3/3600000
        val min =tempTime3%3600000/60000
        val sec=tempTime3%3600000%60000/1000

        var timerLeftText="$hour :"
        if(min<10)timerLeftText+="0"
        timerLeftText+="$min :"
        if(sec<10)timerLeftText+="0"
        timerLeftText+="$sec"
        binding?.timerText3?.text=timerLeftText
    }
    private fun viewMode2(mode:String){
        firstState2=true

        if (mode=="start2"){
            binding?.settingLayout2?.visibility= View.GONE
            binding?.timerLayout2?.visibility= View.VISIBLE
        }
        else{
            binding?.settingLayout2?.visibility= View.VISIBLE
            binding?.timerLayout2?.visibility= View.GONE
        }
    }
    private fun startStop2(){
        if(timeRunning2){
            stopTimer2()
        }
        else{
            startTimer2()
        }
    }
    private fun startTimer2(){
        if (firstState2){
            val sHour2= binding?.hourEdit2?.text.toString()
            val sMin2= binding?.minEdit2?.text.toString()
            val sSec2= binding?.secEdit2?.text.toString()
            time2 =(sHour2.toLong()*3600000)+(sMin2.toLong()*60000)+(sSec2.toLong()*1000)+1000
        }
        else{
            time2 = tempTime2
        }
        countDownTimer2= object:CountDownTimer(time2,1000){
            override fun onTick(millisUnitFinshed: Long) {
                tempTime2=millisUnitFinshed
                updateTime2()
            }

            override fun onFinish() {}
        }.start()
        binding?.stopBtn2?.text="일시정지"
        timeRunning2=true
        firstState2=false
    }
    private fun stopTimer2(){
        countDownTimer2.cancel()
        timeRunning2=false
        binding?.stopBtn2?.text="계속"

    }
    private fun updateTime2(){
        val hour2 = tempTime2/3600000
        val min2 =tempTime2%3600000/60000
        val sec2 =tempTime2%3600000%60000/1000

        var timerLeftText2="$hour2 :"
        if(min2<10)timerLeftText2+="0"
        timerLeftText2+="$min2 :"
        if(sec2<10)timerLeftText2+="0"
        timerLeftText2+="$sec2"
        binding?.timerText2?.text=timerLeftText2
    }




    private fun viewMode(mode:String){
        firstState=true

        if (mode=="start"){
            binding?.settingLayout?.visibility= View.GONE
            binding?.timerLayout?.visibility= View.VISIBLE
        }
        else{
            binding?.settingLayout?.visibility= View.VISIBLE
            binding?.timerLayout?.visibility= View.GONE
        }
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