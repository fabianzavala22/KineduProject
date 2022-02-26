package com.example.kineduproject.remote

import com.example.kineduproject.models.Activities
import com.example.kineduproject.models.ActivityDetail
import com.example.kineduproject.vo.Resource
import com.example.kineduproject.webservice.APIClient
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    suspend fun getActivities(skill_id: Int, baby_id: Long): Resource.Success<Activities> {
        return Resource.Success(APIClient.webService.getActivities(skill_id, baby_id))
    }

    suspend fun getActivityDetail(id: Int,baby_id: Long, locale: String): Resource.Success<ActivityDetail> {
        return Resource.Success(APIClient.webService.getActivityDetail(id, baby_id, locale))
    }

}