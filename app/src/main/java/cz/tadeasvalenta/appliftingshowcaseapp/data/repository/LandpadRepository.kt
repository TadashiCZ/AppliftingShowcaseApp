package cz.tadeasvalenta.appliftingshowcaseapp.data.repository

import cz.tadeasvalenta.appliftingshowcaseapp.data.local.LandpadDao
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.LandpadRemoteDataSource
import cz.tadeasvalenta.appliftingshowcaseapp.util.performGetOperation
import javax.inject.Inject

class LandpadRepository @Inject constructor(
    private val remoteDataSource: LandpadRemoteDataSource,
    private val localDataSource: LandpadDao,
) {
    fun getLandpad(id: String) = performGetOperation(
        { localDataSource.getLandpad(id) },
        { remoteDataSource.getLandpad(id) },
        { localDataSource.insert(it) }
    )

    fun getLandpads() = performGetOperation(
        { localDataSource.getAllLandpads() },
        { remoteDataSource.getLandpads() },
        { localDataSource.insertAll(it) }
    )
}
