package com.muzz.muzzchat.modules

import android.content.Context
import com.muzz.muzzchat.respositories.ChatRepository
import com.muzz.muzzchat.storage.MyRoomDatabase
import com.muzz.muzzchat.storage.dao.ChatMessageDao
import com.muzz.muzzchat.usecases.ChatRepositoryImpl
import com.muzz.muzzchat.usecases.ChatUseCase
import com.muzz.muzzchat.usecases.ChatUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent
    @Provides
    fun provideMyRoomDatabase(
        @ApplicationContext app: Context
    ) = MyRoomDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideMessageDao(db: MyRoomDatabase) = db.chatMessageDao()

    @Singleton
    @Provides
    fun provideChatRepository(chatMessageDao: ChatMessageDao): ChatRepository = ChatRepositoryImpl(chatMessageDao)

    @Singleton
    @Provides
    fun provideChatUseCase(chatRepository: ChatRepository): ChatUseCase = ChatUseCaseImpl(chatRepository)
}