package com.example.kineduproject.webservice

import com.example.kineduproject.models.Activities
import com.example.kineduproject.models.ActivityDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WebService {

    @Headers(
        "Authorization: Token token=63107e52b8077d2c301d661910ddd9acbe5a5de0cfcc36fa7db6815f1c0d4c7fb6fd20bcbb41de29844ea7e59c6f8bbd96902e3bd61c82ed194c108c69f3c7b7"
    )
    @GET("catalogue/activities")
    suspend fun getActivities(
        @Query("skill_id") skill_id: Int,
        @Query("baby_id") baby_id: Long
    ):Activities

    @Headers(
        "Authorization: Token token=63107e52b8077d2c301d661910ddd9acbe5a5de0cfcc36fa7db6815f1c0d4c7fb6fd20bcbb41de29844ea7e59c6f8bbd96902e3bd61c82ed194c108c69f3c7b7"
    )
    @GET("activities/{id}/")
    suspend fun getActivityDetail(
        @Path("id") id: Int,
        @Query("baby_id") baby_id: Long,
        @Query("locale") locale: String
    ):ActivityDetail


}