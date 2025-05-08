package com.example.photosearchapp.features.search.data.repository

import com.example.photosearchapp.features.main.data.remote.api.PhotoApi
import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.features.search.domain.repository.SearchRepository
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi,
) : SearchRepository {
    override suspend fun getSearchList(
        fetchFromRemote: Boolean,
        query: String,
        page: Int,
        apiKey: String
    ): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))

            val remotePhotoList = try {
                photoApi.getSearchPhotoList(query, page, apiKey).photos.map { photo ->
                    photo
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Success(remotePhotoList))

            emit(Resource.Loading(false))
        }
    }

}