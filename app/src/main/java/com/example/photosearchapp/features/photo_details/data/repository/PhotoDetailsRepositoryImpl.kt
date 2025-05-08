package com.example.photosearchapp.features.photo_details.data.repository

import com.example.photosearchapp.features.main.data.local.PhotoDatabase
import com.example.photosearchapp.features.main.data.mapper.toPhoto
import com.example.photosearchapp.features.main.data.remote.dto.PhotoDto
import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.features.photo_details.data.remote.api.PhotoDetailsApi
import com.example.photosearchapp.features.photo_details.domain.repository.PhotoDetailsRepository
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoDetailsRepositoryImpl @Inject constructor(
    private val extraDetailsApi: PhotoDetailsApi,
    photoDb: PhotoDatabase
) : PhotoDetailsRepository {

    private val photoDao = photoDb.photoDao

    override suspend fun getDetails(
        id: Int,
        apiKey: String
    ): Flow<Resource<Photo>> {

        return flow {

            emit(Resource.Loading(true))

            val photoEntity = photoDao.getPhotoById(id = id)

            val doDetailsExist = photoEntity.id != 0

            if (doDetailsExist) {
                emit(
                    Resource.Success(
                        data = photoEntity.toPhoto(
                            id = photoEntity.id
                        )
                    )
                )

                emit(Resource.Loading(false))
                return@flow
            }

            val remoteDetails = fetchRemoteForDetails(
                id = id,
                apiKey = apiKey
            )

            if (remoteDetails == null) {
                emit(
                    Resource.Success(
                        data = with(photoEntity) {
                            toPhoto(
                                id = id
                            )
                        }
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }

            remoteDetails.let {

                photoDao.updatePhotoItem(photoEntity)

                emit(
                    Resource.Success(
                        data = with(photoEntity) {
                            toPhoto(
                                id = id,
                            )
                        }
                    )
                )

                emit(Resource.Loading(false))
            }
        }

    }

    private suspend fun fetchRemoteForDetails(
        id: Int,
        apiKey: String
    ): PhotoDto? {
        val remoteDetails = try {
            extraDetailsApi.getDetails(
                id = id,
                apiKey = apiKey
            )
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            null
        }

        return remoteDetails
    }

}










