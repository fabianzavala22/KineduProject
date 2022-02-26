package com.example.kineduproject.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kineduproject.di.ActivityScope
import com.example.kineduproject.models.Activities
import com.example.kineduproject.models.ActivityDetail
import com.example.kineduproject.repository.Repository
import com.example.kineduproject.vo.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@ActivityScope
class ActivitiesViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _activitiesResponse: MutableLiveData<Resource<Activities>> = MutableLiveData()
    private val _detailResponse: MutableLiveData<Resource<ActivityDetail>> = MutableLiveData()

    val activitiesResponse: LiveData<Resource<Activities>>
        get() = _activitiesResponse

    val detailResponse: LiveData<Resource<ActivityDetail>>
        get() = _detailResponse

    fun getActivities(skill_id: Int, baby_id: Long) = viewModelScope.launch {
        _activitiesResponse.value = Resource.Loading()
        try {
            _activitiesResponse.value = repository.getActivities(skill_id, baby_id)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _activitiesResponse.value = Resource.Failure(e)
        }
    }

    fun getActivityDetail(id: Int, baby_id: Long, locale: String) = viewModelScope.launch {
        _detailResponse.value = Resource.Loading()
        try {
            _detailResponse.value = repository.getActivityDetail(id, baby_id, locale)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            Log.e("fail: ", e.cause?.message.toString())
            _detailResponse.value = Resource.Failure(e)
        }
    }

}