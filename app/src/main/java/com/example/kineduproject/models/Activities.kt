package com.example.kineduproject.models

data class Activities (
    val data: Data
)

data class Data (
    val id:  Int,
    val name: String,
    val type: String,
    val activities: List<Activity>
)

data class Activity (
    val id: Int,
    val name: String,
    val age: Int,
    val age_group: String,
    val activity_type: String,
    val is_holiday: Boolean,
    val domain_id: Int,
    val area_id: Int,
    val min_age: Int,
    val purpose: String,
    val thumbnail: String,
    val active_milestones: Int,
    val completed_milestones: Int,
    val locked: Boolean,
    val dap_lifes_checked: Boolean
)