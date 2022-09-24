package com.example.nasagallery.di

import android.content.Context
import com.example.nasagallery.repository.GalleryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context = appContext

    @Singleton
    @Provides
    fun provideGalleryRepository(context: Context):GalleryRepository = GalleryRepository(context)

}