package com.example.object_oriented_pj_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.object_oriented_pj_10.databinding.FragmentExerciseBinding
import kotlinx.android.synthetic.main.fragment_exercise.*
import kotlinx.android.synthetic.main.list_exercise.*


class ExerciseFragment : Fragment() {

    var binding: FragmentExerciseBinding?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var restTime = 0
        var exerciseTime = 0
        var setNum = 0
        val list = arrayListOf<ExerciseList>();

        binding?.recExercise?.layoutManager = LinearLayoutManager(context)
        binding?.recExercise?.adapter = ExerciseAdapter(list)


        //startButton을 누르면 ExerciseTimer로 넘어감
        binding?.startButton?.setOnClickListener() {
            val f = Fragment()
            val bundle = Bundle()

            bundle.putParcelableArrayList("exercise", list) // list 넘기기
            f.arguments = bundle
            findNavController().navigate(R.id.action_exerciseFragment_to_exerciseTimer, bundle)
            }


        //운동시간 정하는 버튼 클릭 이벤트

        binding?.setExerciseTime?.setOnClickListener {

            val dialog = AlertDialog.Builder(it.context).create()

            val edialog: LayoutInflater = LayoutInflater.from(it.context)
            val mView: View = edialog.inflate(R.layout.timemodal, null)

            val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min2)
            val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec2)

            val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no2)
            val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok2)
            // editText 설정해제
            minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            //최소값 설정
            minute.minValue = 0
            second.minValue = 0

            //최대값 설정
            minute.maxValue = 30
            second.maxValue = 59
            //기본값 설정
            minute.value = 1
            second.value = 0


            //취소버튼
            cancel.setOnClickListener {
                dialog.dismiss()
                dialog.cancel()
            }


            start.setOnClickListener {
                exerciseTime = minute.value * 60 +second.value
                setExerciseTime.setText(minute.value.toString()+"분 "+second.value.toString()+"초");
                dialog.dismiss()
            }


            dialog.setView(mView)

            dialog.create()
            dialog.show()

        }

        binding?.setSets?.setOnClickListener  {
            val dialog = AlertDialog.Builder(it.context).create()

            val edialog: LayoutInflater = LayoutInflater.from(it.context)
            val mView: View = edialog.inflate(R.layout.setmodal, null)

            val second: NumberPicker = mView.findViewById(R.id.number_picker)

            val cancel: Button = mView.findViewById<Button>(R.id.btn_cancel)
            val start: Button = mView.findViewById<Button>(R.id.btn_ok)

            second.minValue = 0
            second.maxValue = 59

            second.value =1

            cancel.setOnClickListener {
                dialog.dismiss()
            }

            start.setOnClickListener {
                setNum = second.value
                setSets.setText(second.value.toString()+"개");
                dialog.dismiss()
            }

            dialog.setView(mView)

            dialog.create()
            dialog.show()

        }

        binding?.setRestTime?.setOnClickListener {

            val dialog = AlertDialog.Builder(it.context).create()

            val edialog: LayoutInflater = LayoutInflater.from(it.context)
            val mView: View = edialog.inflate(R.layout.timemodal2, null)

            val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min2)
            val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec2)

            val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no2)
            val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok2)
            // editText 설정해제
            minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            //최소값 설정
            minute.minValue = 0
            second.minValue = 0

            //최대값 설정
            minute.maxValue = 30
            second.maxValue = 59
            //기본값 설정
            minute.value = 1
            second.value = 0


            //취소버튼
            cancel.setOnClickListener {
                dialog.dismiss()
                dialog.cancel()
            }


            start.setOnClickListener {
                restTime = minute.value * 60 +second.value
                setRestTime.setText(minute.value.toString()+"분 "+second.value.toString()+"초");

                dialog.dismiss()
            }


            dialog.setView(mView)

            dialog.create()
            dialog.show()

        }

        fun addTask(){
            var exercise = ExerciseList(binding?.setExerciseTime?.text.toString(), restTime, exerciseTime);

            for(i: Int in 1..setNum){
                list.add(exercise)
            }

            binding?.recExercise?.adapter?.notifyDataSetChanged()
        }



        binding?.addButton?.setOnClickListener {
            addTask()
        }


    }
}