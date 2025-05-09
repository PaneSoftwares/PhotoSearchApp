package com.example.photosearchapp.features.main.data.repository

import com.example.photosearchapp.features.main.data.local.PhotoDatabase
import com.example.photosearchapp.features.main.data.mapper.toPhoto
import com.example.photosearchapp.features.main.data.mapper.toPhotoEntity
import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.features.main.domain.repository.MainRepository
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    photoDb: PhotoDatabase
) : MainRepository {

    private val photoDao = photoDb.photoDao

    override suspend fun insertItem(photo: Photo) {
        val photoEntity = photo.toPhotoEntity()

        photoDao.insertPhotoItem(
            photoItem = photoEntity
        )
    }

    override suspend fun getItem(
        id: Int
    ): Photo {
        return photoDao.getPhotoById(id).toPhoto(
            id = id,
        )
    }

    override suspend fun getPhotoList(

    ): Flow<Resource<List<Photo>>> {
        return flow {

            emit(Resource.Loading(true))

            val remotePhotoList = try {
                photoDao.getPhotoList(
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Error loading data"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Error loading data"))
                emit(Resource.Loading(false))
                return@flow
            }

            remotePhotoList.let { photoList ->

                val entities = photoList.map {
                    it.toPhoto(
                        id = it.id
                    )
                }

                emit(
                    Resource.Success(data = entities)
                )
                emit(Resource.Loading(false))
            }
        }
    }

}






