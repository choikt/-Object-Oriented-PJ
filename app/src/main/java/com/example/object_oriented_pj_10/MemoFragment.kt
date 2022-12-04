package com.example.object_oriented_pj_10

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.object_oriented_pj_10.databinding.FragmentMemoBinding
import com.example.object_oriented_pj_10.repository.MyMemoRepository
import kotlinx.android.synthetic.main.fragment_memo.*
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.util.*

class MemoFragment: Fragment() {
    val repository = MyMemoRepository();

    val viewModel: MemoView by viewModels()
    var binding: FragmentMemoBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoBinding.inflate(inflater)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.newMemos.observe(viewLifecycleOwner){
            binding?.showButton?.text = it.size.toString()
            binding?.memos?.adapter?.notifyDataSetChanged()
        }

        send_button.setOnClickListener(){
            repository.postMemo(binding?.txtTitle?.text.toString(),LocalDate.now().toString(), binding?.txtBody?.text.toString(),
                 )
        }
        binding?.showButton?.setOnClickListener{
            viewModel.getPosts()
        }

        binding?.memos?.layoutManager = LinearLayoutManager(context)
        binding?.memos?.adapter = MemoAdapter(viewModel.newMemos)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}