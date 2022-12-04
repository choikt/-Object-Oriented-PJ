package com.example.object_oriented_pj_10.STUDY

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.object_oriented_pj_10.R
import com.example.object_oriented_pj_10.databinding.FragmentStudyCountupBinding

class StudyCountUpFragment : Fragment() {

    var binding:FragmentStudyCountupBinding?=null

    //멈춘 시각 저장
    var pauseTime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyCountupBinding.inflate(inflater)

        return binding?.root
    }

    // Study CountUp Timer 에서 보여지는 버튼 3가지에 따른 실행 함수 호출.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.startBtn?.setOnClickListener {
            startButton()
        }

        binding?.stopBtn?.setOnClickListener {
            stopButton()
        }

        binding?.resetBtn?.setOnClickListener {
            resetButton()
        }
        val f = Fragment()
        val bundle = Bundle()
        binding?.countdownBtn?.setOnClickListener {
            f.arguments = bundle
            findNavController().navigate(R.id.action_studyFragment_to_studyFragment2, bundle)
        }
    }

    // 시작 버튼 눌렀을때.
    private fun startButton() {
        binding?.chronometer?.base = SystemClock.elapsedRealtime() + pauseTime
        binding?.chronometer?.start()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = true
        binding?.resetBtn?.isEnabled = true
        binding?.startBtn?.isEnabled = false
    }
    
    // 멈춤 버튼 눌렀을때.
    private fun stopButton() {
        pauseTime = binding?.chronometer?.base!! - SystemClock.elapsedRealtime()
        binding?.chronometer?.stop()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = false
        binding?.resetBtn?.isEnabled = true
        binding?.startBtn?.isEnabled = true
    }
    
    // 리셋 버튼 눌렀을때.
    private fun resetButton() {
        // elapsedRealtime: 부팅 이후의 밀리초를 리턴 (절전 모드에서 보낸 시간 포함)
        pauseTime = 0L
        binding?.chronometer?.base = SystemClock.elapsedRealtime()
        binding?.chronometer?.stop()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = false
        binding?.resetBtn?.isEnabled = false
        binding?.startBtn?.isEnabled = true
    }
}