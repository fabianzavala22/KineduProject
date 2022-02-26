package com.example.kineduproject.models

data class ActivityDetail (
    val data: ActivityData
)

data class ActivityData (
    val activity: ActivityInfo
)

data class ActivityInfo (
    val id: Int,
    val name: String,
    val age: Int,
    val age_group: String,
    val purpose: String,
    val description: String,
    val activity_themes: List<Themes>
)

data class Themes(
    val id: Int,
    val color: String,
    val image: String ,
    val name: String,
    val description: String,
)