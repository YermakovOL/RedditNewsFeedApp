package yermakov.oleksii.redditnewsfeed.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import yermakov.oleksii.redditnewsfeed.di.NetworkModule

@OptIn(ExperimentalCoroutinesApi::class)
class RedditDaoTest {
    private val redditDao : RedditDao = RedditDao(NetworkModule.provideRedditApi())
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun fetchTopPosts() = runTest{
        println(redditDao.fetchTopPosts(50))
    }
}