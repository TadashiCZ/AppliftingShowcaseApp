package cz.tadeasvalenta.appliftingshowcaseapp.data.remote

import javax.inject.Inject

class CapsuleRemoteDataSource @Inject constructor(
    private val capsuleService: CapsuleService
) : DataSource() {
    suspend fun getCapsules() = getResult { capsuleService.getAllCapsules() }
    suspend fun getCapsule(id: String) = getResult { capsuleService.getCapsule(id) }
}
