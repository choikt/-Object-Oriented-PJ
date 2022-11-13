package com.example.object_oriented_pj_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.object_oriented_pj_10.databinding.FragmentEntryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EntryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var binding:FragmentEntryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEntryBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnCook?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_cookFragment)
        }
        binding?.btnStudy?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_sutdyFragment)
        }
        binding?.btnExercise?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_exerciseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding =null
    }

}