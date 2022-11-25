package com.example.object_oriented_pj_10.Study

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.object_oriented_pj_10.databinding.FragmentStudyBinding

class StudyFragment : Fragment() {

    var binding: FragmentStudyBinding?=null

    //멈춘 시각 저장
    var pauseTime = 0L


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
        // elapsedRealtime: 부팅 이후의 밀리초를 리턴 (절전 모드에서 보낸 시간 포함)
        // 사용자가 현재시간을 수정해도 영향 받지 않음
        binding?.startBtn?.setOnClickListener {
            startButton()
        }

        binding?.stopBtn?.setOnClickListener {
            stopButton()
        }


        binding?.resetBtn?.setOnClickListener {
            resetButton()
        }
    }

    private fun resetButton() {
        pauseTime = 0L
        binding?.chronometer?.base = SystemClock.elapsedRealtime()
        binding?.chronometer?.stop()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = false
        binding?.resetBtn?.isEnabled = false
        binding?.startBtn?.isEnabled = true
    }

    private fun stopButton() {
        pauseTime = binding?.chronometer?.base!! - SystemClock.elapsedRealtime()
        binding?.chronometer?.stop()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = false
        binding?.resetBtn?.isEnabled = true
        binding?.startBtn?.isEnabled = true
    }

    private fun startButton() {
        binding?.chronometer?.base = SystemClock.elapsedRealtime() + pauseTime
        binding?.chronometer?.start()

        //버튼 표시 여부 조정
        binding?.stopBtn?.isEnabled = true
        binding?.resetBtn?.isEnabled = true
        binding?.startBtn?.isEnabled = false
    }

}