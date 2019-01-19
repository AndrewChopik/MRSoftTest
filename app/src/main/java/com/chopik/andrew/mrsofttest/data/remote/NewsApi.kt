package com.chopik.andrew.mrsofttest.data.remote

import com.chopik.andrew.mrsofttest.data.remote.models.Response
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {

    @GET("everything")
    fun requestNews(): Single<Response>

}