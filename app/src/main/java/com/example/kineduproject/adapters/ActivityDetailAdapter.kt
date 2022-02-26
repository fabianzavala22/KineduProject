package com.example.kineduproject.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kineduproject.R
import com.example.kineduproject.databinding.ActivitiesAdapterBinding
import com.example.kineduproject.databinding.DetailAdapterBinding
import com.example.kineduproject.models.Activity
import com.example.kineduproject.models.Themes

class ActivityDetailAdapter(private val themes: List<Themes>): RecyclerView.Adapter<ActivityDetailAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.detail_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val theme = themes[position]
        holder.bind(theme)
    }

    override fun getItemCount(): Int = themes.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = DetailAdapterBinding.bind(view)

        fun bind(theme: Themes) {

            binding.titleThemeTv.text = theme.name
            binding.descriptionTv.text = theme.description
            Glide.with(itemView.context).load(theme.image).centerCrop().into(binding.themeImv)
        }
    }
}