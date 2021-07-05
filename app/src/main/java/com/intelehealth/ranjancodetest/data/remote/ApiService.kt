package com.intelehealth.ranjancodetest.data.remote

import com.intelehealth.ranjancodetest.data.remote.model.RepositoryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Branch related apis//// bookings
    @GET(ApiPath.repos)
    suspend fun getRepositoryList(@Path("ORG")org:String): Response<RepositoryListResponse>
}