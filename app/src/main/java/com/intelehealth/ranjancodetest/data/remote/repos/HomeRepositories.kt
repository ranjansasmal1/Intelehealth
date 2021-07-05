package com.intelehealth.ranjancodetest.data.remote.repos

import com.intelehealth.ranjancodetest.data.remote.ApiService
import com.intelehealth.ranjancodetest.data.remote.NetworkResult
import com.intelehealth.ranjancodetest.data.remote.model.RepositoryListResponse
import javax.inject.Inject

class HomeRepositories @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRepoList(org:String): NetworkResult<RepositoryListResponse> {
        val response = apiService.getRepositoryList(org)
        if (response.isSuccessful)
            return NetworkResult.Success(response.body()!!)
        return NetworkResult.Failure(response)
    }
}