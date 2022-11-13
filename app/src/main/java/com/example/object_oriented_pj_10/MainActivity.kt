package com.example.object_oriented_pj_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.object_oriented_pj_10.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /*lateinit var binding:ActivityMainBinding

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
    }*/
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navController=binding.frgnav.getFragment<NavHostFragment>().navController
        setupActionBarWithNavController(navController)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=binding.frgnav.getFragment<NavHostFragment>().navController
        return navController.navigateUp()||super.onSupportNavigateUp()
    }
}