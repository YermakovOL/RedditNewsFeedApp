package yermakov.oleksii.redditnewsfeed.dao

import yermakov.oleksii.redditnewsfeed.client.RedditApi
import yermakov.oleksii.redditnewsfeed.domain.RedditPost
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDao @Inject constructor(private val api: RedditApi) {
    suspend fun fetchTopPosts(limit: Int = 50): List<RedditPost>? {
        return try {
            val redditApiResponse = api.getTopPosts(limit)
            redditApiResponse.listingData.children.map { child -> child.data } ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}