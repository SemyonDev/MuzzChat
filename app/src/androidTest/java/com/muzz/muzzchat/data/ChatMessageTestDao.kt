package com.muzz.muzzchat.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.muzz.muzzchat.storage.MyRoomDatabase
import com.muzz.muzzchat.storage.dao.ChatMessageDao
import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@ExperimentalCoroutinesApi
@SmallTest
class ChatMessageTestDao {

    // A JUnit Test Rule that swaps the background executor
    // used by the Architecture Components with a different one which executes each task synchronously
    @get:Rule(order = 0)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Define HiltRule
    @get:Rule(order = 1) // define order
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test-db")
    lateinit var myDB: MyRoomDatabase
    private lateinit var chatMessageDao: ChatMessageDao

    // execute before every test case
    @Before
    fun setup() {
        hiltRule.inject()
        chatMessageDao = myDB.chatMessageDao()
    }

    // execute after every test case
    @After
    fun teardown() {
        myDB.close()
    }

    @Test
    @Throws(Exception::class)
    fun add_read_ChatMessage2() = runBlocking {
        val chatMessageEntity = ChatMessageEntity( id = 1, message = "Test message", type = 1)
        chatMessageDao.insertChatMessage(chatMessageEntity)
        val chatMessageList = chatMessageDao.getChatMessageList()
        val job = async(Dispatchers.IO) {
            chatMessageList.collect {
                assertThat(it).contains(ChatMessageEntity( id = 1, message = "Test message", type = 1))
            }
        }
        job.join()
    }

}