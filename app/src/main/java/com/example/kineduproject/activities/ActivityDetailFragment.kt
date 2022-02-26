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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kineduproject.MainActivity
import com.example.kineduproject.adapters.ActivityDetailAdapter
import com.example.kineduproject.databinding.FragmentActivityDetailBinding
import com.example.kineduproject.vo.Resource
import com.google.android.material.bottomsheet.BottomSheetBehavior
import javax.inject.Inject

class ActivityDetailFragment : Fragment() {

    private var activityId: Int = 0
    private lateinit var image: String
    @Inject
    lateinit var viewModel: ActivitiesViewModel

    private var _binding: FragmentActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {data ->
            activityId = data.getInt("id")
            image = data.getString("image").toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  bsb = BottomSheetBehavior.from(binding.bottomSheet);
        binding.showDescriptionBtn.setOnClickListener {
            bsb.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.btnClose.setOnClickListener {
            bsb.state = BottomSheetBehavior.STATE_HIDDEN
        }

        initRecycler()

        val baby_id: Long = 7740260
        val locale = "en"

        viewModel.getActivityDetail(activityId, baby_id, locale)
        viewModel.detailResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("data", result.data.toString())
                    binding.progresBar.visibility = View.GONE
                    Glide.with(requireContext()).load(image).centerCrop().into(binding.activityImv)
                    binding.titleTv.text = result.data.data.activity.name
                    binding.purposeTv.text = result.data.data.activity.purpose
                    binding.descriptionTv.text = result.data.data.activity.description
                    binding.rvThemes.adapter = ActivityDetailAdapter(result.data.data.activity.activity_themes)
                }
                is Resource.Failure -> {
                    Log.d("data", result.toString())
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecycler(){
        binding.rvThemes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvThemes.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}