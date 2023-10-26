package ua.marchuk.photoBuzzDailySnapshot.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.LocalDatabase
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoDao
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoInfoRepository
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providePhotoDao(application: Application): PhotoDao {
        val database = databaseBuilder(application, LocalDatabase::class.java, "photo_database")
            .build()
        return database.photoDao()
    }

    @Provides
    @Singleton
    fun providePhotoRepository(photoDao: PhotoDao): PhotoRepository {
        return PhotoRepository(photoDao)
    }

    @Provides
    @Singleton
    fun providePhotoInfoRepository(): PhotoInfoRepository {
        return PhotoInfoRepository()
    }


}