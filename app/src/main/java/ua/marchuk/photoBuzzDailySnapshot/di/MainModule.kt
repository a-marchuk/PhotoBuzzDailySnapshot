package ua.marchuk.photoBuzzDailySnapshot.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoDao
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoDatabase
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providePhotoDao(application: Application): PhotoDao {
        val database = databaseBuilder(application, PhotoDatabase::class.java, "photo_database")
            .build()
        return database.photoDao()
    }

    @Singleton
    @Provides
    fun providePhotoRepository(photoDao: PhotoDao): PhotoRepository {
        return PhotoRepository(photoDao)
    }
}