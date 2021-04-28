package cz.tadeasvalenta.appliftingshowcaseapp.data.remote

import javax.inject.Inject

class LandpadRemoteDataSource @Inject constructor(
    private val landpadService: LandpadService
) : DataSource() {
    suspend fun getLandpads() = getResult { landpadService.getAllLandpads() }
    suspend fun getLandpad(id: String) = getResult { landpadService.getLandpad(id) }
}
