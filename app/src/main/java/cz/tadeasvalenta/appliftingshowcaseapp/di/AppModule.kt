package cz.tadeasvalenta.appliftingshowcaseapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.tadeasvalenta.appliftingshowcaseapp.data.local.AppDatabase
import cz.tadeasvalenta.appliftingshowcaseapp.data.local.CapsuleDao
import cz.tadeasvalenta.appliftingshowcaseapp.data.local.LandpadDao
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.CapsuleRemoteDataSource
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.CapsuleService
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.LandpadRemoteDataSource
import cz.tadeasvalenta.appliftingshowcaseapp.data.remote.LandpadService
import cz.tadeasvalenta.appliftingshowcaseapp.data.repository.CapsuleRepository
import cz.tadeasvalenta.appliftingshowcaseapp.data.repository.LandpadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCapsuleService(retrofit: Retrofit): CapsuleService =
        retrofit.create(CapsuleService::class.java)

    @Provides
    fun provideLandpadService(retrofit: Retrofit): LandpadService =
        retrofit.create(LandpadService::class.java)

    @Singleton
    @Provides
    fun provideCapsuleRemoteDataSource(capsuleService: CapsuleService) =
        CapsuleRemoteDataSource(capsuleService)

    @Singleton
    @Provides
    fun provideLandpadRemoteDataSource(landpadService: LandpadService) =
        LandpadRemoteDataSource(landpadService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCapsuleDao(db: AppDatabase) = db.capsuleDao()

    @Singleton
    @Provides
    fun provideLandpadDao(db: AppDatabase) = db.landpadDao()

    @Singleton
    @Provides
    fun provideCapsuleRepository(
        remoteDataSource: CapsuleRemoteDataSource,
        localDataSource: CapsuleDao
    ) = CapsuleRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideLandpadRepository(
        remoteDataSource: LandpadRemoteDataSource,
        localDataSource: LandpadDao
    ) = LandpadRepository(remoteDataSource, localDataSource)
}
