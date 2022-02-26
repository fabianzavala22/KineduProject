package com.example.kineduproject.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kineduproject.MainActivity
import com.example.kineduproject.adapters.ActivitiesAdapter
import com.example.kineduproject.databinding.FragmentActivitiesBinding
import com.example.kineduproject.vo.Resource
import javax.inject.Inject


class ActivitiesFragment : Fragment() {

    @Inject lateinit var viewModel: ActivitiesViewModel
    private var _binding: FragmentActivitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        val skill_id = 5
        val baby_id: Long = 7740260

        viewModel.getActivities(skill_id, baby_id)
        viewModel.activitiesResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("data", result.data.toString())
                    binding.progresBar.visibility = View.GONE
                    binding.rvActivities.adapter = ActivitiesAdapter(result.data.data.activities )
                }
                is Resource.Failure -> {
                    Log.d("data", result.exeption.toString())
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecycler(){
        binding.rvActivities.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}