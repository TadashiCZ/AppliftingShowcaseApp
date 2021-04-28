package cz.tadeasvalenta.appliftingshowcaseapp.data.repository

import cz.tadeasvalenta.appliftingshowcaseapp.data.local.CapsuleDao
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.CapsuleRemoteDataSource
import cz.tadeasvalenta.appliftingshowcaseapp.util.performGetOperation
import javax.inject.Inject

class CapsuleRepository @Inject constructor(
    private val remoteDataSource: CapsuleRemoteDataSource,
    private val localDataSource: CapsuleDao,
) {
    fun getCapsule(id: String) = performGetOperation(
        { localDataSource.getCapsule(id) },
        { remoteDataSource.getCapsule(id) },
        { localDataSource.insert(it) }
    )

    fun getCapsules() = performGetOperation(
        { localDataSource.getAllCapsules() },
        { remoteDataSource.getCapsules() },
        { localDataSource.insertAll(it) }
    )
}
