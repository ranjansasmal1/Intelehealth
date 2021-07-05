package com.intelehealth.ranjancodetest.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.intelehealth.ranjancodetest.data.remote.NetworkResult
import com.intelehealth.ranjancodetest.data.remote.model.RepositoryListResponse
import com.intelehealth.ranjancodetest.data.remote.repos.HomeRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepositories: HomeRepositories):
    ViewModel(), LifecycleObserver {
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    private var job: Job? = null


    fun getPopularRepos(org:String) = liveData<NetworkResult<RepositoryListResponse>>(Dispatchers.IO) {
        this.emit(NetworkResult.Loading(loadings = null))
        try {
            this.emit(homeRepositories.getRepoList(org))
        }catch (e:Exception){
            this.emit(NetworkResult.Failure(errorResponse = null))
        }
    }
}