package com.example.object_oriented_pj_10

import android.content.Context
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.example.object_oriented_pj_10.databinding.FragmentGameBinding
import kotlinx.android.synthetic.*
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var binding:FragmentGameBinding?=null
    private var currentCountDownTimer: CountDownTimer? = null
    private var tickingSoundId: Int? = null
    private var bellSoundId: Int? = null
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private val seekBar: SeekBar by lazy {
        mainActivity.findViewById(R.id.seekBar)
    }

    private val range=(1..60)
    private val soundPool = SoundPool.Builder().build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSounds()
        bindViews()
        binding?.btnRandomSec?.setOnClickListener {
            stopCountDown()//새로운 타이머 시작 멈춤
            updateRemainTime(range.random().toString().toLong()*60*1000L)
            startCountDownrandom(range.random().toString().toLong())
        }
        binding?.btnRandomMin?.setOnClickListener {
            stopCountDown()
            updateRemainTime(range.random().toString().toLong()*6000*1000L)
            startCountDownrandom(range.random().toString().toLong()*60)
        }


    }
    override fun onResume() {
        super.onResume()
        soundPool.autoResume()
    }

    override fun onPause() {
        super.onPause()
        soundPool.autoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // sound파일들이 메모리에서 제거된다.
        soundPool.release()
    }

    private fun bindViews() {
        binding?.seekBar?.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    // 사용자가 seek바를 변경한 경우 시간을 변경해준다.
                    if (fromUser){
                        // progress의 단위는 분이기 때문에
                        updateRemainTime(progress*60*1000L)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // 스크롤을 통해 다시 타이머를 시작하는 경우
                    // 현재 카운트 다운을 멈춘다.
                    stopCountDown()

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar?:return
                    // 사용자의 드래그가 멈췄을 경우
                    // 카운트 다운 시작 여부를 결정합니다.
                    if (seekBar.progress ==0){
                        // 0분이면 시작 안함
                        stopCountDown()
                    }else{
                        // 0분이 아니면 시작함
                        startCountDown()
                    }
                }

            }
        )
    }

    private fun stopCountDown() {
        // 카운트 다운 타이머를 멈추고 사운드 Pool도 멈춥니다.
        currentCountDownTimer?.cancel()
        currentCountDownTimer = null
        soundPool.autoPause()
    }

    private fun startCountDown(){
        currentCountDownTimer = createCountDownTimer(seekBar.progress*60*1000L)
        currentCountDownTimer?.start()

        tickingSoundId?.let {soundId->
            soundPool.play(soundId,1F,1F,0,-1,1F)
        }
    }
    private fun startCountDownrandom(long:Long){
        currentCountDownTimer = createCountDownTimer(long*1000)
        currentCountDownTimer?.start()

        tickingSoundId?.let {soundId->
            soundPool.play(soundId,1F,1F,0,-1,1F)
        }
    }

    private fun createCountDownTimer(initialMills:Long) =
        // 1초 마다 호출되도록 함
        object : CountDownTimer(initialMills, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                completeCountDown()
            }

        }

    private fun completeCountDown(){
        updateRemainTime(0)
        updateSeekBar(0)


        // 끝난 경우
        // 끝난 벨소리 재생함
        soundPool.autoPause()
        bellSoundId?.let {soundId->
            soundPool.play(soundId, 1F,1F,0,0,1F)
        }
    }

    // 기본적으로 함수마다 초의 단위를 통일하는게 좋음. 개발할 때 가독성이 좋음
    private fun updateRemainTime(remainMillis: Long){
        // 총 남은 초
        val remainSeconds = remainMillis/1000

        // 분만 보여줌, 초만 보여줌
        binding?.remainMinutesTextView?.text = "%02d:".format(remainSeconds/60)
        binding?.remainSecondsTextView?.text= "%02d".format(remainSeconds%60)

    }

    private fun initSounds() {
        // 사운드 파일을 로드함
        tickingSoundId = soundPool.load(mainActivity, R.raw.timer_ticking, 1)
        bellSoundId = soundPool.load(mainActivity, R.raw.timer_bell, 1)
    }

    private fun updateSeekBar(remainMillis: Long) {
        // 밀리 세컨드를 분(정수)으로 바꿔서 보여줌
        seekBar.progress = (remainMillis / 1000 / 60).toInt()
    }
}




