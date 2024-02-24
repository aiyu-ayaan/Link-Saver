package com.atech.core.di

import android.content.Context
import androidx.room.Room
import com.atech.core.room.LinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideLinkDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        LinkDatabase::class.java,
        LinkDatabase.DATABASE_NAME
    ).build()

    @Provides
    fun provideLinkDao(
        database: LinkDatabase
    ) = database.dao

}