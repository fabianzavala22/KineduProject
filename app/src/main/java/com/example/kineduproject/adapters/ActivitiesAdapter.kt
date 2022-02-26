package com.example.kineduproject.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kineduproject.R
import com.example.kineduproject.databinding.ActivitiesAdapterBinding
import com.example.kineduproject.models.Activity
import androidx.navigation.fragment.findNavController

class ActivitiesAdapter(private val activities: List<Activity>): RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activities_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activities[position]
        holder.bind(activity)

        holder.itemView.setOnClickListener {

            var bundle = Bundle()
            bundle.putInt("id",activity.id)
            bundle.putString("image", activity.thumbnail)

            findNavController(holder.itemView).navigate(R.id.activityDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int = activities.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ActivitiesAdapterBinding.bind(view)

        fun bind(activity: Activity) {

            binding.nameTv.text = activity.name
            //binding.activityTypeTv.text = activity.activity_type
            binding.ageTv.text = "age " + activity.age_group.toString()
            binding.purposeTv.text = activity.purpose
            Glide.with(itemView.context).load(activity.thumbnail).centerCrop().into(binding.activityImv)
        }
    }
}