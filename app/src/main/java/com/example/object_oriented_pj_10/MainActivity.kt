package com.example.object_oriented_pj_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.object_oriented_pj_10.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    lateinit var countDownTimer: CountDownTimer
    lateinit var countDownTimer2: CountDownTimer


    var timeRunning = false
    var firstState=false

    var timeRunning2 = false
    var firstState2=false

    var time =0L
    var tempTime= 0L

    var time2 =0L
    var tempTime2= 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            viewMode("start")
            startStop()
        }
        binding.stopBtn.setOnClickListener{
            startStop()
        }
        binding.cancelBtn.setOnClickListener{
            viewMode("cancel")
            stopTimer()
        }
        binding.startBtn2.setOnClickListener{
            viewMode("start")
            startStop2()
        }
        binding.stopBtn2.setOnClickListener{
            startStop2()
        }
        binding.cancelBtn2.setOnClickListener{
            viewMode("cancel")
            stopTimer2()
        }
    }

    private fun viewMode(mode:String){
        firstState=true

        if (mode=="start"){
            binding.settingLayout.visibility= View.GONE
            binding.timerLayout.visibility= View.VISIBLE
        }
        else{
            binding.settingLayout.visibility= View.VISIBLE
            binding.timerLayout.visibility= View.GONE
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
            val sHour= binding.hourEdit.text.toString()
            val sMin= binding.minEdit.text.toString()
            val sSec= binding.secEdit.text.toString()
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
        binding.stopBtn.text="일시정지"
        timeRunning=true
        firstState=false
    }
    private fun stopTimer(){
        countDownTimer.cancel()
        timeRunning=false
        binding.stopBtn.text="계속"

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
        binding.timerText.text=timerLeftText
    }

    private fun viewMode2(mode:String){
        firstState2=true

        if (mode=="start"){
            binding.settingLayout2.visibility= View.GONE
            binding.timerLayout2.visibility= View.VISIBLE
        }
        else{
            binding.settingLayout2.visibility= View.VISIBLE
            binding.timerLayout2.visibility= View.GONE
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
            val sHour= binding.hourEdit2.text.toString()
            val sMin= binding.minEdit2.text.toString()
            val sSec= binding.secEdit2.text.toString()
            time2 =(sHour.toLong()*3600000)+(sMin.toLong()*60000)+(sSec.toLong()*1000)+1000
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
        binding.stopBtn2.text="일시정지"
        timeRunning2=true
        firstState2=false
    }
    private fun stopTimer2(){
        countDownTimer2.cancel()
        timeRunning2=false
        binding.stopBtn2.text="계속"

    }
    private fun updateTime2(){
        val hour = tempTime2/3600000
        val min =tempTime2%3600000/60000
        val sec =tempTime2%3600000%60000/1000

        var timerLeftText="$hour :"
        if(min<10)timerLeftText+="0"
        timerLeftText+="$min :"
        if(sec<10)timerLeftText+="0"
        timerLeftText+="$sec"
        binding.timerText2.text=timerLeftText
    }
}