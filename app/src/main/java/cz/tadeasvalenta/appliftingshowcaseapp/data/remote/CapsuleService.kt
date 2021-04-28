package cz.tadeasvalenta.appliftingshowcaseapp.data.remote

import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Capsule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CapsuleService {

    @GET("capsules")
    suspend fun getAllCapsules(): Response<List<Capsule>>

    @GET("capsules/{id}")
    suspend fun getCapsule(@Path("id") id: String): Response<Capsule>
}
