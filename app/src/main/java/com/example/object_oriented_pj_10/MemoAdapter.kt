package com.example.object_oriented_pj_10;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.object_oriented_pj_10.databinding.MemoListBinding

class MemoAdapter(val memos: LiveData<ArrayList<MemoList>>)
    : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {
    class ViewHolder(val binding: MemoListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: MemoList?){
            memo?.let {
                binding.setTitle.text = it.title
                binding.date.text = it.date
                binding.description.text = it.description
            }
        }
    }
    //한 칸이 viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MemoListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(memos?.value?.getOrNull(position))
    }

    override fun getItemCount(): Int = memos.value?.size ?: 0
}