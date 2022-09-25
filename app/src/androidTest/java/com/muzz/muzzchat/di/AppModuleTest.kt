package com.muzz.muzzchat.di

import android.content.Context
import androidx.room.Room
import com.muzz.muzzchat.storage.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    // Provider database object for instrument testing
    @Provides
    @Named("test-db")
    fun provideUserDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, MyRoomDatabase::class.java)
            .allowMainThreadQueries().build()
}