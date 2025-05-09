package com.example.photosearchapp.di

import android.app.Application
import androidx.room.Room
import com.example.photosearchapp.features.main.data.local.PhotoDatabase
import com.example.photosearchapp.features.main.data.remote.api.PhotoApi
import com.example.photosearchapp.features.main.data.remote.api.PhotoApi.Companion.BASE_URL
import com.example.photosearchapp.features.photo_details.data.remote.api.PhotoDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun providePhotoDatabase(app: Application): PhotoDatabase {
        return Room.databaseBuilder(
            app,
            PhotoDatabase::class.java,
            "photos.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providePhotoApi(): PhotoApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(PhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideExtraDetailsApi(): PhotoDetailsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(PhotoDetailsApi::class.java)
    }

}









