package com.example.object_oriented_pj_10

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.object_oriented_pj_10.databinding.FragmentExerciseBinding


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


        var selectNum = 0
        var exerciseTime = 0
        val list = arrayListOf<ExerciseList>()
        var map = mutableMapOf<String,List<Int>>();

        binding?.recExercise?.layoutManager = LinearLayoutManager(context)
        binding?.recExercise?.adapter = ExerciseAdapter(list)


        //전환할 화면은 ExerciseTimer이다.


        //같이 가져갈 데이터는 리스트 형태이다.
        //
        //            bundle.putParcelableArrayList("exercise", intentlist)
        //




        //startButton을 누르면 ExerciseTimer로 넘어감
        binding?.startButton?.setOnClickListener() {
            println(list);
            val f = Fragment()
            val bundle = Bundle()


            bundle.putParcelableArrayList("exercise", list) // list 넘기기

            f.arguments = bundle


            }


        //세트 수 정하는 버튼 클릭 이벤트
        binding?.SetCount?.setOnClickListener  {
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
                selectNum = second.value
                dialog.dismiss()
            }

            dialog.setView(mView)

            dialog.create()
            dialog.show()

        }
        binding?.SetTime?.setOnClickListener {

            val dialog = AlertDialog.Builder(it.context).create()

            val edialog: LayoutInflater = LayoutInflater.from(it.context)
            val mView: View = edialog.inflate(R.layout.timemodal, null)

            val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min)
            val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec)

            val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no)
            val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok)
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

                dialog.dismiss()
            }


            dialog.setView(mView)

            dialog.create()
            dialog.show()

        }
        fun addTask(){
            var exercise = ExerciseList(binding?.setName?.text.toString(), selectNum, exerciseTime)

            list.add(exercise)
            //startIntent.putExtra("type",1)
//            val bool = 1
//            secondFragment.setFragmentResult("booleanKey", bundleOf("booleanKey" to bool))


            binding?.recExercise?.adapter?.notifyDataSetChanged()
        }

        binding?.addButton?.setOnClickListener {

            addTask()
            //startIntent.putExtra("map", map);
        }

        //intent시 back 버튼으로 전으로 돌아오기

    }
}