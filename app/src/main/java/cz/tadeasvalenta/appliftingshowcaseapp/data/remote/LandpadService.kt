package cz.tadeasvalenta.appliftingshowcaseapp.data.remote

import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LandpadService {

    @GET("landpads")
    suspend fun getAllLandpads(): Response<List<Landpad>>

    @GET("landpads/{id}")
    suspend fun getLandpad(@Path("id") id: String): Response<Landpad>
}
