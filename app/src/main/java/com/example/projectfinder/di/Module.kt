package com.example.projectfinder.di

import android.app.Application
import androidx.room.Room
import com.example.projectfinder.projectfinder.data.local.ProjectDatabase
import com.example.projectfinder.projectfinder.data.repository.ProjectRepositoryImpl
import com.example.projectfinder.projectfinder.domain.repository.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Provides
    @Singleton
    fun provideProjectDatabase(app: Application): ProjectDatabase {
        return Room.databaseBuilder(
            app,
            ProjectDatabase::class.java,
            "project.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProjectRepository( db: ProjectDatabase ): ProjectRepository {
        return ProjectRepositoryImpl(db.projectDao)
    }
}