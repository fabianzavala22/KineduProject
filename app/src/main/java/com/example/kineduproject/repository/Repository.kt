package com.example.kineduproject.repository

import com.example.kineduproject.models.Activities
import com.example.kineduproject.models.ActivityDetail
import com.example.kineduproject.remote.RemoteDataSource
import com.example.kineduproject.vo.Resource
import com.example.kineduproject.webservice.APIClient
import dagger.Provides
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getActivities(skill_id: Int, baby_id: Long): Resource<Activities> {
        return remoteDataSource.getActivities(skill_id, baby_id)
    }

    suspend fun getActivityDetail(
        id: Int,
        baby_id: Long,
        locale: String
    ): Resource<ActivityDetail> {
        return remoteDataSource.getActivityDetail(id, baby_id, locale)
    }

}
